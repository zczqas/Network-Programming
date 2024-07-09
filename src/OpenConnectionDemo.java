import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OpenConnectionDemo {
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                System.out.print((char) i);
            }

            System.out.println("\nURL HEADER :: Content Type: " + connection.getContentType());
            System.out.println("URL HEADER :: Content Encoding: " + connection.getContentEncoding());
            System.out.println("URL HEADER :: Content Length: " + connection.getContentLength());
        } catch (IOException e) {
            System.out.println("IOException : " + e);
            }
    }
}
