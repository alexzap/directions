package mobi.ingogo.interview.service.geocoder;

import mobi.ingogo.interview.model.Position;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class GeocoderServiceTest {
    private GeocoderService geocoderService;

    @Before
    public void setUp() {
        geocoderService = new GeocoderService("AIzaSyBzlLYISGjL_ovJwAehh6ydhB56fCCpPQw");
    }

    @Test
    public void geocode1Test() {
        Position position = new Position(-33.864933, 151.204426);
        GeocodeResult result = geocoderService.reverseGeocode(position);
        assertThat(result.getSuburb(), is("Sydney"));
        assertThat(result.getStreetAddress(), is("55 Clarence St, Sydney NSW 2000, Australia"));

    }

    @Test
    public void geocode2Test() {
        Position position = new Position(-33.81291023, 151.1759779);
        GeocodeResult result = geocoderService.reverseGeocode(position);
        assertThat(result.getSuburb(), IsNull.nullValue());
        assertThat(result.getStreetAddress(), is("Sydney Orbital Network & Pacific Hwy & M2 & Lane Cove Tunnel, New South Wales, Australia"));
    }

    @Test
    public void geocode3Test() {
        Position position = new Position(-27.45898348, 153.01174678);
        GeocodeResult result = geocoderService.reverseGeocode(position);
        assertThat(result.getSuburb(), IsNull.nullValue());
        assertThat(result.getStreetAddress(), is("Musgrave Rd & M3 & Hale St & State Route 31, Queensland, Australia"));
    }

    @Test
    public void geocode4Test() {
        Position position = new Position(-37.73281596, 144.90839894);
        GeocodeResult result = geocoderService.reverseGeocode(position);
        assertThat(result.getSuburb(), is("Essendon Fields"));
        assertThat(result.getStreetAddress(), is("120 Bulla Rd, Essendon Fields VIC 3041, Australia"));
    }

    @Test
    public void geocode5Test() {
        Position position = new Position(-27.48449974, 153.0342487);
        GeocodeResult result = geocoderService.reverseGeocode(position);
        assertThat(result.getSuburb(), IsNull.nullValue());
        assertThat(result.getStreetAddress(), is("Vulture St & State Route 41 & Pacific Mwy & M3, Queensland, Australia"));
    }

}
