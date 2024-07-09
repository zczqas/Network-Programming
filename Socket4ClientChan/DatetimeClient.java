import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DatetimeClient {
    public static void main(String[] args) {
//        try (Socket socket = new Socket("time.nist.gov", 13)){
        try (Socket socket = new Socket("localhost", 13001)){
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.US_ASCII);
            for (int i = reader.read(); i != -1; i = reader.read()) {
                time.append((char) i);
            }
            System.out.println(time);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
