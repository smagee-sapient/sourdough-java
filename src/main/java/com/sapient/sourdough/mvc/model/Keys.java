package com.sapient.sourdough.mvc.model;

public class Keys {
	/**
	 * Default constructor
	 */
	public Keys() {
		// empty
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param p256dh
	 * @param auth
	 */
	public Keys(String p256dh, String auth) {
		this.p256dh = p256dh;
		this.auth = auth;
	}

	private String p256dh;

	private String auth;

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

	@Override
	public String toString() {
		return "Keys [p256dh=" + p256dh + ", auth=" + auth + "]";
	}
}
