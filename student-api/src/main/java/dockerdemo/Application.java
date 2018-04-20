package dockerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author Allan G. Ramirez (aramirez@lingotek.com)
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application extends SpringBootServletInitializer {
    private static Logger LOG = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(Application.class);
        String activeProfiles = Optional.ofNullable(System.getProperty("spring.profiles.active")).orElse("");
        //
        Pattern pattern = Pattern.compile("[\\.+,]*overrides[,\\.+]*");
        if (!pattern.matcher(activeProfiles).find()) {
            // Will return false if not "prod,overrides,test" or "overrides,test" or "prod,overrides" or "overrides"
            // User should never include overrides as active profile. This is just a workaround for developers to
            // override or add properties when running the app locally
            springApplication.setAdditionalProfiles("overrides");
        }
        springApplication.run(args);
    }

    // http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-traditional-deployment
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
