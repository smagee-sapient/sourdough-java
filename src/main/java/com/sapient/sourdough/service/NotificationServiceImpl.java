package com.sapient.sourdough.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sapient.sourdough.data.entity.SubscriptionEntity;
import com.sapient.sourdough.data.repository.SubscriptionRepository;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Service
public class NotificationServiceImpl implements NotificationService {
	protected static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private OWM owm;
	private PushService pushService = new PushService();

	@Inject
	private SubscriptionRepository subscriptionRepository;

	@Value("${privateKey}")
	private String privateKey;

	@Value("${publicKey}")
	private String publicKey;

	@Value("${openweathermap.api.key}")
	private String apiKey;

	@PostConstruct
	public void postConstruct() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		// Add BouncyCastle as an algorithm provider
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}

		owm = new OWM(apiKey);

		pushService.setPrivateKey(privateKey);
		pushService.setPublicKey(publicKey);
	}

	@Override
	public void sendNotification(String payload)
			throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
		for (SubscriptionEntity subscription : subscriptions) {
			Notification notification = new Notification(subscription.getEndpoint(), subscription.getP256dh(),
					subscription.getAuth(), payload);
			pushService.send(notification);
		}
	}

	@Scheduled(cron = "* * * * *")
	public void sendNotifications() throws NumberFormatException, APIException, GeneralSecurityException, IOException,
			JoseException, ExecutionException, InterruptedException {
		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		logger.debug("running scheduled job at {}:{}", hour, minute);

		for (SubscriptionEntity subscription : subscriptionRepository.findByHourAndMinute(Integer.toString(hour),
				Integer.toString(minute))) {
			String message = getCurrentWeather(Float.parseFloat(subscription.getLat()),
					Float.parseFloat(subscription.getLon()));
			Notification notification = new Notification(subscription.getEndpoint(), subscription.getP256dh(),
					subscription.getAuth(), message);
			pushService.send(notification);
		}
	}

	public String getCurrentWeather(float lat, float lon) throws APIException {
		CurrentWeather cwd = owm.currentWeatherByCoords(lat, lon);
		StringBuilder sb = new StringBuilder();
		sb.append("It's going to be ");
		int temperatureInFahrenheit = kelvinToFahrenheit(cwd.getMainData().getTempMax());
		sb.append(temperatureInFahrenheit);
		sb.append(" today. You should wear ");
		sb.append(getClothing(temperatureInFahrenheit));
		sb.append(" and ");
		if (cwd.hasRainData() && cwd.getRainData().hasPrecipVol3h() && cwd.getRainData().getPrecipVol3h() > 0) {
			sb.append("take your umbrella.");
		} else {
			sb.append("leave your umbrella at home.");
		}

		return sb.toString();
	}

	String getClothing(int temperatureInFahrenheit) {
		if (temperatureInFahrenheit > 80) {
			return "shorts";
		} else if (temperatureInFahrenheit > 67) {
			return "short sleeves";
		} else if (temperatureInFahrenheit > 50) {
			return "long sleeves";
		}
		return "a coat";
	}

	/**
	 * Method to convert from degrees Kelvin to degrees Celcius.
	 * 
	 * @param degKelvin
	 * @return
	 */
	float kelvinToCelciusFloat(float degKelvin) {
		return degKelvin - 273.15f;
	}

	/**
	 * Method to convert from degrees Celcius to degrees Fahrenheit.
	 * 
	 * @param degCelcius
	 * @return
	 */
	float celciusToFahrenheit(float degCelcius) {
		return degCelcius * 9 / 5 + 32;
	}

	/**
	 * Method to convert a float to an int (truncate decimal).
	 * 
	 * @param value
	 * @return
	 */
	int floatToInt(float value) {
		return (int) value;
	}

	/**
	 * Method to convert from degrees Kelvin to degrees Celcius.
	 * 
	 * @param degKelvin
	 * @return
	 */
	int kelvinToCelcius(float degKelvin) {
		return (int) kelvinToCelciusFloat(degKelvin);
	}

	/**
	 * Method to convert from degrees Kelvin to degrees Fahrenheit.
	 * 
	 * @param degKelvin
	 * @return
	 */
	int kelvinToFahrenheit(float degKelvin) {
		return floatToInt(celciusToFahrenheit(kelvinToCelciusFloat(degKelvin)));
	}
}
