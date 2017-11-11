package com.sapient.sourdough.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sapient.sourdough.data.entity.SubscriptionEntity;
import com.sapient.sourdough.data.repository.SubscriptionRepository;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Service
public class NotificationServiceImpl implements NotificationService {
	protected static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private PushService pushService = new PushService();

	@Inject
	private SubscriptionRepository subscriptionRepository;

	@Value("${privateKey}")
	private String privateKey;

	@Value("${publicKey}")
	private String publicKey;

	@PostConstruct
	public void postConstruct() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		// Add BouncyCastle as an algorithm provider
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}

		pushService.setPrivateKey(privateKey);
		pushService.setPublicKey(publicKey);
	}

	@Override
	public void sendNotification(String payload)
			throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
		for (SubscriptionEntity subscription : subscriptions) {
			Notification notification = new Notification(subscription.getEndpoint(), subscription.getP256dh(), subscription.getAuth(), payload);
			pushService.send(notification);
		}
	}
}
