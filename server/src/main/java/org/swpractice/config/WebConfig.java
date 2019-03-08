package org.swpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.swpractice.util.ApplicationContextSupplier;

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
}
