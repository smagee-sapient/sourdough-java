package com.sapient.sourdough.mvc.model;

public class SubscriptionModel {
	/**
	 * Default constructor
	 */
	public SubscriptionModel() {
		this.keys = new Keys();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param endpoint
	 * @param keys
	 */
	public SubscriptionModel(String endpoint, Keys keys) {
		this.endpoint = endpoint;
		this.keys = keys;
	}

	private String endpoint;

	private Keys keys;

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
