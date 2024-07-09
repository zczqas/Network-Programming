import java.net.URI;
import java.net.URISyntaxException;

public class TestURI {
    public static void main(String[] args) {
        try {
            String uri_base = "https://www.google.com/";
            String uri_relative = "search?q=java";
            String uri_complete = uri_base + uri_relative;

            URI base = new URI(uri_base);
            URI relative = new URI(uri_relative);

            URI complete = base.resolve(relative);

            System.out.println("Complete URI: " + complete);
            System.out.println("Scheme: " + complete.getScheme());
            System.out.println("SSP: " + complete.getSchemeSpecificPart());
            System.out.println("Host: " + complete.getHost());
            System.out.println("Port: " + complete.getPort());
            System.out.println("Path: " + complete.getPath());
            System.out.println("Query: " + complete.getQuery());
            System.out.println("Fragment: " + complete.getFragment());

            URI uri_create = new URI(uri_complete);
            System.out.println("URI create: " + uri_create);
        }catch (URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        }
    }
}
