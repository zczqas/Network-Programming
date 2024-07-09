import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleResponseCache extends ResponseCache {
    private final Map<URI, SimpleCacheResponse> cacheMap = new HashMap<>();

    @Override
    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {
        System.out.println("Fetching from Response Cache for URI: " + uri);
        return cacheMap.get(uri);
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        System.out.println("Putting response in cache for URI: " + uri);
        return new SimpleCacheRequest(uri);
    }

    private class SimpleCacheRequest extends CacheRequest {
        private final ByteArrayOutputStream out = new ByteArrayOutputStream();
        private final URI uri;
        private boolean aborted = false;

        public SimpleCacheRequest(URI uri) {
            this.uri = uri;
        }

        @Override
        public OutputStream getBody() {
            return new FilterOutputStream(out) {
                @Override
                public void close() throws IOException {
                    if (!aborted) {
                        System.out.println("Caching response for URI: " + uri);
                        cacheMap.put(uri, new SimpleCacheResponse(out.toString().getBytes()));
                    }
                    super.close();
                }
            };
        }

        @Override
        public void abort() {
            aborted = true;
        }
    }

    private static class SimpleCacheResponse extends CacheResponse {
        private final byte[] data;

        public SimpleCacheResponse(byte[] data) {
            this.data = data;
        }

        @Override
        public Map<String, List<String>> getHeaders() {
            return Collections.emptyMap();
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(data);
        }
    }

    public static void main(String[] args) throws IOException {
        ResponseCache.setDefault(new SimpleResponseCache());
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
        URLConnection con = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        System.out.println("First Request:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        HttpURLConnection cachedConn = (HttpURLConnection) url.openConnection();
        BufferedReader cachedReader = new BufferedReader(new InputStreamReader(cachedConn.getInputStream()));

        System.out.println("Second Request (from cache):");
        while ((line = cachedReader.readLine()) != null) {
            System.out.println(line);
        }
        cachedReader.close();
    }
}
