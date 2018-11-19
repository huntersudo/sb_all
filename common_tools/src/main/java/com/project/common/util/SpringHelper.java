package com.project.common.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Wang Zheng
 */
@Component
public class SpringHelper implements ApplicationContextAware {
    private static final Logger LOG = LoggerFactory.getLogger(SpringHelper.class);

    private static ApplicationContext ac;

    private SpringHelper() {
    }

    @SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringHelper.ac = applicationContext;
        LOG.info("spring application context initialized...");
    }

    /**
     * Get the current application context.
     *
     * @return the current application context
     */
    public static ApplicationContext getApplicationContext() {
        return SpringHelper.ac;
    }

    /**
     * Get the bean corresponding to the specified name.
     *
     * @return the bean with the specified name
     */
    public static Object getBean(final String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * Get the bean corresponding to the specified class type.
     *
     * @return the bean with the specified class type
     */
    public static <T> T getBean(final Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * Get the bean corresponding to the specified name and class type.
     *
     * @return the bean with the specified name and class type
     */
    public static <T> T getBean(final String name, final Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}