package org.shisen.web.commn.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 * @author shishi
 */
@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer addCorsMappings() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowCredentials(true)
						.allowedMethods("GET", "POST")
						.maxAge(3600);
			}
		};
	}
}