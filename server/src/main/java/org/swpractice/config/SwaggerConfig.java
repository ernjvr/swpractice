package org.swpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static org.swpractice.util.Constants.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(API_INFO_TITLE, API_INFO_DESCRIPTION, API_INFO_VERSION,
            API_INFO_TOS,DEFAULT_CONTACT, API_INFO_LICENCE, API_INFO_LICENCE_URL, new ArrayList<>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(API_DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(API_DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
