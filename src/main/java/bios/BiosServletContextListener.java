package bios;

import bios.persist.PersistenceModule;
import bios.template.TemplateModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

public class BiosServletContextListener extends GuiceServletContextListener {
    static final Logger LOG = LoggerFactory.getLogger(BiosServletContextListener.class);

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new BiosServletModule(), new PersistenceModule(), new TemplateModule());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("context initialized");
        super.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("context destroyed");
        super.contextDestroyed(servletContextEvent);
    }
}
