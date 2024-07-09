import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {
    public static void main(String[] args) {
//        try {
//            URL url = new URL("https://www.google.com");
//            String path = "/search";
//
//            URL complete_url = new URL(url, path);
//
//            System.out.println(complete_url);
//        }catch (MalformedURLException e) {
//            System.out.println(e.getMessage());
//        }

        try {
            String protocol = "https";
            String host = "www.google.com";
//            int port = 80;
            String path = "/search?q=hello+world";

//            URL url = new URL(protocol, host, port, path);
            URL url = new URL(protocol, host, path);
            System.out.println(url);
        }catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
}
