package bios.filter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Serve static content. This is implemented to avoid incompatible DefaultServlet implementations in various containers.
 * </p>
 * <p>
 * Mapping to this filter requires a static resource to be found and served. It does *not* continue the filter chain.
 * </p>
 */
public class StaticContent extends AbstractHttpFilter {

    /**
     * static types served by Driven server.
     */
    static final Map<String, String> STATIC_TYPES = new HashMap<String, String>() {{
        put("css", "text/css");
        put("csv", "text/csv");
        put("gif", "image/gif");
        put("html", "text/html");
        put("ico", "image/x-icon");
        put("jpeg", "image/jpeg");
        put("jpg", "image/jpeg");
        put("js", "application/javascript");
        put("json", "application/json");
        put("less", "text/plain");
        put("map", "application/json");
        put("pdf", "application/pdf");
        put("png", "image/png");
        put("svg", "image/svg+xml");
        put("swf", "application/x-shockwave-flash");
        put("txt", "text/plain");
        put("text", "text/plain");
    }};
    private static final Logger LOG = LoggerFactory.getLogger(StaticContent.class);
    private CacheManager cacheManager = new CacheManager();

    /**
     * Return the names of the types this Filter will statically serve.
     *
     * @return the Set of names of types
     */
    public static Set<String> types() {
        return STATIC_TYPES.keySet();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get path relative to servlet context
        final String path = request.getServletPath();

        final String ext = path.substring(path.lastIndexOf("."));
        final String type = (ext != null && ext.length() > 1) ? ext.substring(1) : ext;
        final String mime = STATIC_TYPES.get(type);

        if (mime == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // set the Content-Type header
        response.setContentType(mime);

        final String filePath = filterConfig.getServletContext().getRealPath(path);
        final byte[] content = cacheManager.load(filePath);
        if (content == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // write to output
        final OutputStream output = response.getOutputStream();
        try {
            IOUtils.write(content, output);
        } finally {
            IOUtils.closeQuietly(output);
        }
        // intentionally does not continue the FilterChain.
    }

    static class CacheManager {
        final Map<String, CacheItem> cache = new HashMap<String, CacheItem>();

        public byte[] load(final String path) {
            final File file = new File(path);

            // File may have been removed after being cached.
            if (!file.exists()) {
                cache.remove(path);
                LOG.warn("{} does not exist", path);
                return null;
            }

            CacheItem cacheItem = cache.get(path);
            if (cacheItem == null || cacheItem.getTimestamp() != file.lastModified()) {
                try {
                    LOG.debug("loading {} into cache", path);
                    cacheItem = CacheItem.readFile(file);
                    cache.put(path, cacheItem);
                } catch (IOException exception) {
                    LOG.error("error reading file", exception);
                }
            }

            // Return contents, null means it was not found
            return cacheItem != null ? cacheItem.getContent() : null;
        }
    }

    static class CacheItem {
        private byte[] content;
        private long timestamp;

        private CacheItem(final byte[] content, final long timestamp) {
            this.content = content;
            this.timestamp = timestamp;
        }

        public static CacheItem readFile(final File file) throws IOException {
            final long timestamp = file.lastModified();
            final byte[] content = FileUtils.readFileToByteArray(file);
            return new CacheItem(content, timestamp);
        }

        public byte[] getContent() {
            return content;
        }

        public long getTimestamp() {
            return timestamp;
        }

    }

}

