package com.sapient.sourdough.mvc.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.sourdough.data.entity.SubscriptionEntity;
import com.sapient.sourdough.data.repository.SubscriptionRepository;
import com.sapient.sourdough.mvc.model.SubscriptionModel;

@RestController
public class SubscriptionController {
	protected static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Inject
	private SubscriptionRepository subscriptionRepository;

	/**
	 * Create a subscription
	 */
	@PostMapping(value = "/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody SubscriptionModel subscriptionModel) {
		logger.debug(subscriptionModel.toString());
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();

		subscriptionEntity.setLat(subscriptionModel.getCoord().getLat());
		subscriptionEntity.setLon(subscriptionModel.getCoord().getLon());
		subscriptionEntity.setHour(subscriptionModel.getHour());
		subscriptionEntity.setMinute(subscriptionModel.getMinute());
		subscriptionEntity.setEndpoint(subscriptionModel.getEndpoint());
		subscriptionEntity.setAuth(subscriptionModel.getKeys().getAuth());
		subscriptionEntity.setP256dh(subscriptionModel.getKeys().getP256dh());

		subscriptionRepository.save(subscriptionEntity);
	}
}
