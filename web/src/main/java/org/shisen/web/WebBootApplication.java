package org.shisen.web;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("org.shisen.web.mapper")
@SpringBootApplication
public class WebBootApplication {

	public static void main(String[] args) {
		// SpringApplication.run(WebBootApplication.class, args);
		new SpringApplicationBuilder(WebBootApplication.class)
				.bannerMode(Banner.Mode.CONSOLE)
				.run(args);
	}

}
