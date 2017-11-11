package com.sapient.sourdough.mvc.model;

public class SubscriptionModel {
	/**
	 * Default constructor
	 */
	public SubscriptionModel() {
		this.coord = new Coord();
		this.keys = new Keys();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param coord
	 * @param hour
	 * @param minute
	 * @param endpoint
	 * @param keys
	 */
	public SubscriptionModel(Coord coord, String hour, String minute, String endpoint, Keys keys) {
		this.coord = coord;
		this.hour = hour;
		this.minute = minute;
		this.endpoint = endpoint;
		this.keys = keys;
	}

	private Coord coord;
	private String hour;
	private String minute;
	private String endpoint;
	private Keys keys;

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
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

	public Keys getKeys() {
		return keys;
	}

	public void setKeys(Keys keys) {
		this.keys = keys;
	}
}
