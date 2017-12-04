package mobi.ingogo.interview.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Simon on 27/11/17.
 */

@Embeddable
public class GeoPositionDto {
	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "accuracy")
	private int accuracy;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
}
