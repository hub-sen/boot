package org.shisen.web.commn.config.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * swagger 配置
 *
 * @author shisen
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final Environment environment;

	@Value("${project.version}")
	private String version;

	public SwaggerConfig(Environment environment) {this.environment = environment;}

	@SuppressWarnings("all")
	@Bean
	public Docket swaggerDocketUser() {
		Predicate<String> userPredicate = PathSelectors.ant("/user/**");
		return createSwaggerGroup("用户相关", userPredicate);
	}

	@SuppressWarnings("all")
	private Docket createSwaggerGroup(@NotNull String groupName, Predicate... predicates) {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(enableSwagger())
				.apiInfo(apiInfo())
				.groupName(groupName)
				.ignoredParameterTypes(HttpSession.class)
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(Predicates.or(predicates))
				.build();
	}

	private boolean enableSwagger() {
		Profiles profiles = Profiles.of("dev", "test");
		return environment.acceptsProfiles(profiles);
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("施森",
				"http://hub-sen.github.io",
				"1107474494@qq.com");

		return new ApiInfo("BOOT接口文档",
				"测试使用swagger",
				version,
				"http://localhost:8080/swagger-ui.html",
				contact,
				"",
				"",
				new ArrayList<>()
		);
	}

}
