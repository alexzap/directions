package mobi.ingogo.interview.dto;

/**
 * Created by Simon on 27/11/17.
 */
public class RouteResponseDto {
	private GeoPositionDto pickup;
	private GeoPositionDto dropoff;
	private String encodedPolyline;
	private String duration;
	private String distance;

	public GeoPositionDto getPickup() {
		return pickup;
	}

	public void setPickup(GeoPositionDto pickup) {
		this.pickup = pickup;
	}

	public GeoPositionDto getDropoff() {
		return dropoff;
	}

	public void setDropoff(GeoPositionDto dropoff) {
		this.dropoff = dropoff;
	}

	public String getEncodedPolyline() {
		return encodedPolyline;
	}

	public void setEncodedPolyline(String encodedPolyline) {
		this.encodedPolyline = encodedPolyline;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
}
