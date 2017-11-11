package com.sapient.sourdough.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Root web application configuration.
 * 
 * @author Ryan Powell
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.sapient.sourdough")
@PropertySource(value = { "classpath:application.properties" })
public class RootConfig {
	/**
	 * @return the {@link PropertySourcesPlaceholderConfigurer} bean.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

		// the ignoreUnresolvablePlaceholders property allows
		// us to set default values for the @Value annotation.
		//
		// e.g. @Value("${some.property:default value}")
		properties.setIgnoreResourceNotFound(true);

		return properties;
	}
}
