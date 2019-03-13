package org.swpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.util.ApplicationContextSupplier;
import org.swpractice.util.ApiRequestInterceptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        return new MappedInterceptor(new String[]{"/api/**"}, new ApiRequestInterceptor());
    }
}
