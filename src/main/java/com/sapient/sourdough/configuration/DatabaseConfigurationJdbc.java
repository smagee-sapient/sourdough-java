package com.sapient.sourdough.configuration;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Annotation-based configuration of the Database using JDBC. The important
 * thing here is the component scan of the repository package.
 * 
 * @author Ryan Powell
 */
@Configuration
@EnableJpaRepositories("com.sapient.sourdough.data.repository")
@EnableTransactionManagement
public class DatabaseConfigurationJdbc extends DatabaseConfigurationBase {
	@Value("${jdbc.driver.class}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.connection.username}")
	private String username;
	@Value("${jdbc.connection.password}")
	private String password;

	public DatabaseConfigurationJdbc() {
		logger = LoggerFactory.getLogger(DatabaseConfigurationJdbc.class);
	}

	/**
	 * A {@link DriverManagerDataSource} configuration.
	 * 
	 * @return {@link DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
