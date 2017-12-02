package mobi.ingogo.interview.service.geocoder;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import mobi.ingogo.interview.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeocoderService {

    private GeoApiContext context = new GeoApiContext();

    @Autowired
    public GeocoderService(@Value("${google.maps.key}") String apiKey) {
        context.setApiKey(apiKey);
    }

    public GeocodeResult reverseGeocode(Position position) {

        try {
            GeocodeResult geocodeResult = new GeocodeResult();
            GeocodingApiRequest request = GeocodingApi.newRequest(context)
                    .latlng(new LatLng(position.getLatitude(), position.getLongitude()));
            GeocodingResult result = request.await()[0];
            geocodeResult.setStreetAddress(result.formattedAddress);
            for (AddressComponent addressComponent :result.addressComponents) {
                for (AddressComponentType type : addressComponent.types) {
                    if (AddressComponentType.LOCALITY.equals(type)) {
                        geocodeResult.setSuburb(addressComponent.longName);
                    }
                }
            }

            return geocodeResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
