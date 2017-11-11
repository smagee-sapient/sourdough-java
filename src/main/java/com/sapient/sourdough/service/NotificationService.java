package com.sapient.sourdough.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;

import net.aksingh.owmjapis.api.APIException;

/**
 * Service for sending a Web Push notification.
 * 
 * @author Ryan Powell
 */
public interface NotificationService {
	/**
	 * Send the payload to all subscribed devices.
	 * 
	 * @param payload
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws JoseException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	void sendNotification(String payload)
			throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException;

	/**
	 * Get the weather string based on the provided lat and lon.
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 * @throws APIException
	 */
	String getCurrentWeather(float lat, float lon) throws APIException;
}
