package bios.template;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 *
 */
public class Template {
    private static final String OUTPUT_ENCODING = "UTF-8";
    private final String template;
    private final VelocityContext context;

    public Template(final String template) {
        this.template = template;
        this.context = new VelocityContext();
    }

    public void put(final String key, final Object value) {
        context.put(key, value);
    }

    public void putAll(final Map<String, Object> properties) {
        if (properties == null)
            return;
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
    }

    public String mergeToString() throws IOException {
        final StringWriter writer = new StringWriter();
        try {
            if (merge(writer))
                return writer.getBuffer().toString();
            throw new IOException("could not merge template output");
        } finally {
            IOUtils.closeQuietly(writer);
        }

    }

    public boolean merge(final Writer writer) throws IOException {
        return Velocity.mergeTemplate(template, OUTPUT_ENCODING, context, writer);
    }
}
