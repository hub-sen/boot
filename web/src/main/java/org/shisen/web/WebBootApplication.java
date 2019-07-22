package org.shisen.web;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("org.shisen.web.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class WebBootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WebBootApplication.class)
				.bannerMode(Banner.Mode.CONSOLE)
				.run(args);
	}

}
