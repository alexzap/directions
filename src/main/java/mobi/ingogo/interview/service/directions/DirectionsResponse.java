package mobi.ingogo.interview.service.directions;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.EncodedPolyline;

public class DirectionsResponse {
    private EncodedPolyline encodedPolyline;
    private Distance distance;
    private Duration duration;

    public EncodedPolyline getEncodedPolyline() {
        return encodedPolyline;
    }

    public void setEncodedPolyline(EncodedPolyline encodedPolyline) {
        this.encodedPolyline = encodedPolyline;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
