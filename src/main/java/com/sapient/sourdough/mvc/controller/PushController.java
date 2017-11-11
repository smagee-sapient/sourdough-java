package com.sapient.sourdough.mvc.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.sourdough.mvc.model.Notification;
import com.sapient.sourdough.service.NotificationService;

@RestController
public class PushController {
	protected static final Logger logger = LoggerFactory.getLogger(PushController.class);

	@Inject
	private NotificationService notificationService;

	/**
	 * Send push notification.
	 */
	@PostMapping(value = "/notifications", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void sendNotification(@RequestBody Notification notification)
			throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		notificationService.sendNotification(notification.getData());
	}
}
