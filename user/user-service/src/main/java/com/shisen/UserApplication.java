package com.shisen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

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
