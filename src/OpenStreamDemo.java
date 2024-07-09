import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class OpenStreamDemo {
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
//            URL url = new URL("https://t3.ftcdn.net/jpg/02/09/65/14/360_F_209651427_Moux8Hkey15wtMbtLymbPPrdrLhm58fH.jpg");
//            No Headers in openStream
            InputStream stream = url.openStream();
            int i;

            while ((i = stream.read()) != -1) {
                System.out.print((char) i);
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}
