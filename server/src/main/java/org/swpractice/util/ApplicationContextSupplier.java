package org.swpractice.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextSupplier implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static <T> Object getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }

    public static <T> Object getBean(String qualifier, Class<T> clazz) {
        return CONTEXT.getBean(qualifier, clazz);
    }
}
