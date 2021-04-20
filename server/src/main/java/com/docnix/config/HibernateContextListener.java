package com.docnix.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateConfig.initSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateConfig.endSessionFactory();
    }
}
