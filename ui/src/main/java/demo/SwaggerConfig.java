package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by joao.paulo on 01/07/2016.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    /**
     * Swagger Springfox configuration.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                "Nome",
                "Contact URL",
                "Email");

        ApiInfo apiInfo = new ApiInfo(
                "Title",
                "Description",
                "Version",
                "TermsOfServiceUrl",
                contact,
                "License",
                "LicenseUrl");

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

}
