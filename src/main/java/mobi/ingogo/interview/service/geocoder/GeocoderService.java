package mobi.ingogo.interview.service.geocoder;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;
import mobi.ingogo.interview.dao.IGeopositionDAO;
import mobi.ingogo.interview.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GeocoderService {

    private GeoApiContext context = new GeoApiContext();

    @Autowired
    public GeocoderService(@Value("${google.maps.key}") String apiKey) {
        context.setApiKey(apiKey);
    }

    @Autowired
    private IGeopositionDAO geopositionDAO;

    public GeocodeResult reverseGeocode(Position position) {

        try {
            GeocodeResult geocodeResult = new GeocodeResult();
            LatLng location = new LatLng(position.getLatitude(), position.getLongitude());
            GeocodingApiRequest request = GeocodingApi.reverseGeocode(context, location);
            GeocodingResult[] results = request.await();
            if (results == null || results.length == 0)
                return geocodeResult;
            geocodeResult.setStreetAddress(getStreetAddressFromResults(results));
//            geocodeResult.setStreetAddress(results[0].formattedAddress); // best address is in first result

            // suburb might not be in the first result so loop over them until we find it
            String suburb = getSuburbFromResults(results);
            geocodeResult.setSuburb(suburb);
            geopositionDAO.addGeocodeResult(geocodeResult);

            return geocodeResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getStreetAddressFromResults(GeocodingResult[] results) {
        String streetNumber = null;
        for (GeocodingResult result : results) {
            for (AddressComponent component : result.addressComponents) {
                if (Arrays.stream(component.types).anyMatch(type -> type.equals(AddressComponentType.STREET_NUMBER))) {
                    streetNumber = component.longName;
                }
                if (Arrays.stream(component.types).anyMatch(type -> type.equals(AddressComponentType.ROUTE))) {
                    String route = component.longName;
                    return streetNumber != null ? streetNumber + " " + route : route; // as long as there is a route we have a street address
                }
            }
        }
        return null;
    }

    private String getSuburbFromResults(GeocodingResult[] results) {
        for (GeocodingResult result : results) {
            for (AddressComponent addressComponent :result.addressComponents) {
                for (AddressComponentType type : addressComponent.types) {
                    if (AddressComponentType.LOCALITY.equals(type)) {
                        return addressComponent.longName;
                    }
                }
            }
        }
        return null;
    }
}
