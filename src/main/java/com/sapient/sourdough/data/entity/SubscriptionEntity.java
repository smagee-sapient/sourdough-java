package com.sapient.sourdough.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to store the relevant portions of a push notification subscription.
 * 
 * @author Ryan Powell
 */
@Entity
@Table(name = "subscription")
public class SubscriptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String lat;

	@Column(length = 20, nullable = false)
	private String lon;

	@Column(length = 10, nullable = false)
	private String hour;

	@Column(length = 10, nullable = false)
	private String minute;

	@Column(length = 1024, nullable = false)
	private String endpoint;

	@Column(length = 1024, nullable = false)
	private String p256dh;

	@Column(length = 1024, nullable = false)
	private String auth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getP256dh() {
		return p256dh;
	}

	public void setP256dh(String p256dh) {
		this.p256dh = p256dh;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
