package mobi.ingogo.interview.dto;

import javax.persistence.*;

/**
 * Created by Simon on 27/11/17.
 */
@Entity
@Table(name = "route_request")
public class RouteRequestDto {


	@Id
	@GeneratedValue
	@Column(name = "route_request_id")
	private Integer routeRequestId;

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

	public Integer getRouteRequestId() {
		return routeRequestId;
	}

	public void setRouteRequestId(Integer routeRequestId) {
		this.routeRequestId = routeRequestId;
	}
}
