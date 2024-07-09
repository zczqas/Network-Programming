import java.net.MalformedURLException;
import java.net.URL;

public class UrlAccess {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://example.com/college/index.html?q=about-us");

            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Default Port: " + url.getDefaultPort());
            System.out.println("File: " + url.getFile());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Ref: " + url.getRef());
        }catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        }
    }
}
