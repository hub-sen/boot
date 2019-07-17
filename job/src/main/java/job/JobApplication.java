package job;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishi
 */
@EnableScheduling
@SpringBootApplication
public class JobApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JobApplication.class)
                //.web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
