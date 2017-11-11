package com.sapient.sourdough.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;

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
}
