import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeader {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com/");
        URLConnection conn = url.openConnection();
        int i = 1;
        do {
            System.out.println(conn.getHeaderFieldKey(i) + ": " + conn.getHeaderField(i));
            i = i + 1;
        }while(conn.getHeaderField(i) != null);
    }
}
