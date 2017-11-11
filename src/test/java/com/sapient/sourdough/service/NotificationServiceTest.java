package com.sapient.sourdough.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NotificationServiceTest {
	NotificationServiceImpl service = new NotificationServiceImpl();

	@Test
	public void testKelvinToCelcius() {
		assertThat(0).isEqualTo(service.kelvinToCelcius(273.15f));
		assertThat(100).isEqualTo(service.kelvinToCelcius(373.15f));
	}

	@Test
	public void testKelvinToFarenheight() {
		assertThat(32).isEqualTo(service.kelvinToFahrenheit(273.15f));
		assertThat(212).isEqualTo(service.kelvinToFahrenheit(373.15f));
	}

	@Test
	public void testGetClothing() {
		assertThat("shorts").isEqualTo(service.getClothing(90));
		assertThat("short sleeves").isEqualTo(service.getClothing(75));
		assertThat("long sleeves").isEqualTo(service.getClothing(60));
		assertThat("a coat").isEqualTo(service.getClothing(32));
	}
}
