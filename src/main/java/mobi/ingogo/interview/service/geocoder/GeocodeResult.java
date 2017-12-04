package mobi.ingogo.interview.service.geocoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="geocode_result")
public class GeocodeResult implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="geocode_result_id")
    private Integer geocodeResultId;

    @Column(name="suburb")
    private String suburb;

    @Column(name="street_address")
    private String streetAddress;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Integer getGeocodeResultId() {
        return geocodeResultId;
    }

    public void setGeocodeResultId(Integer geocodeResultId) {
        this.geocodeResultId = geocodeResultId;
    }
}
