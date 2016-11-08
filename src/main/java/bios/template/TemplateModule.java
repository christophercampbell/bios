package bios.template;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 *
 */
public class TemplateModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(VelocityInitializer.class).asEagerSingleton();
    }

    /**
     * Initialize the velocity engine
     */
    static class VelocityInitializer {
        public VelocityInitializer() {
            Properties properties = new Properties();
            properties.put("runtime.log", "/dev/null");
            properties.put("resource.loader", "template");
            properties.put("template.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            properties.put("template.resource.loader.cache", "false");

            Velocity.init(properties);
        }

    }
}
