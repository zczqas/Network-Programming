import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetContentDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://images.dog.ceo/breeds/pyrenees/n02111500_4854.jpg");
            URLConnection connection = url.openConnection();

            String response_type = connection.getContentType();

            Object obj = url.getContent();

            System.out.println("The response type is " + response_type);
            System.out.println(obj.toString());
            System.out.println(obj.getClass().getName());
        }catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
        System.out.println("IOException");
    }
}
}