package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <pre>
 * Description  注册中心
 * </pre>
 */

@SpringBootApplication
@EnableEurekaServer
public class BootRegistry {
	public static void main(String[] args) {
		SpringApplication.run(BootRegistry.class);
	}
}
