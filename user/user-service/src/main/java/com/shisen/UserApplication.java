package com.shisen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.shisen.mapper")
@EnableCaching
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class);
	}
}
