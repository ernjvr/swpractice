package org.swpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.util.ApplicationContextSupplier;
import org.swpractice.util.ApiRequestInterceptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.swpractice.util.Constants.API_ROOT_PATTERN;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public EntityManager entityManager() {
        final EntityManagerFactory entityManagerFactory =
                (EntityManagerFactory) ApplicationContextSupplier.getBean(EntityManagerFactory.class);
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public MappedInterceptor requestInterceptor() {
        return new MappedInterceptor(new String[]{API_ROOT_PATTERN}, new ApiRequestInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
