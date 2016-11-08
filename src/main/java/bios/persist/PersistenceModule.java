package bios.persist;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 *
 */
public class PersistenceModule implements Module {
    static final Logger LOG = LoggerFactory.getLogger(PersistenceModule.class);

    @Override
    public void configure(Binder binder) {
        binder.bind(PersistenceFactory.class).asEagerSingleton();
        binder.bind(RankManager.class).asEagerSingleton();
        binder.bind(TaxonManager.class).asEagerSingleton();

        // Override hibernate properties if present
        final Configuration configuration = new Configuration();
        final Properties properties = configuration.getProperties();
        final Properties system = System.getProperties();
        for (final Object key : system.keySet()) {
            if (key instanceof String && ((String) key).startsWith("hibernate.")) {
                final String prop = (String) key;
                final String value = system.getProperty(prop);
                final String displayValue = prop.contains("password") ? "******" : value;
                LOG.info("OVERRIDE {}={}", prop, displayValue);
                properties.setProperty(prop, value);
            }
        }
        configuration.setProperties(properties);

    }
}
