package mobi.ingogo.interview.dto;

import javax.persistence.*;

/**
 * Created by Simon on 27/11/17.
 */
@Entity
@Table(name = "route_response")
public class RouteResponseDto {

	@Id
	@GeneratedValue
	@Column(name = "route_response_id")
	private Integer routeResponseId;

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name="latitude", column = @Column(name="pickup_latitude") ),
			@AttributeOverride(name="longitude", column = @Column(name="pickup_longitude")),
			@AttributeOverride(name="accuracy", column = @Column(name="pickup_accuracy"))
	} )
	private GeoPositionDto pickup;

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name="latitude", column = @Column(name="dropoff_latitude") ),
			@AttributeOverride(name="longitude", column = @Column(name="dropoff_longitude")),
			@AttributeOverride(name="accuracy", column = @Column(name="dropoff_accuracy"))
	} )
	private GeoPositionDto dropoff;

	@Lob
	@Column(name= "encodedPolyline")
	private String encodedPolyline;

	@Column(name= "duration")
	private String duration;

	@Column(name= "distance")
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

	public Integer getRouteResponseId() {
		return routeResponseId;
	}

	public void setRouteResponseId(Integer routeResponseId) {
		this.routeResponseId = routeResponseId;
	}
}
