package org.shisen.web.commn.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 数据源配置
 * @author shisen
 */
@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.driver-class-name:}")
	private String driverClassName;

	@Value("${spring.datasource.url:}")
	private String url;

	@Value("${spring.datasource.username:}")
	private String userName;

	@Value("${spring.datasource.password:}")
	private String password;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(driverClassName);
		config.addDataSourceProperty("url", url);
		config.addDataSourceProperty("user", userName);
		config.addDataSourceProperty("password", password);
		return new HikariDataSource(config);
	}
}