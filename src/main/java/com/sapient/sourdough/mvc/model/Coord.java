package com.sapient.sourdough.mvc.model;

public class Coord {
	/**
	 * Default constructor
	 */
	public Coord() {
		// empty constructor
	}

	/**
	 * Parameterized constructor
	 */
	public Coord(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}

	private String lat = "38.878337";
	private String lon = "-77.100703d";

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
}
