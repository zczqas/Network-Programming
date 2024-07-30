import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadFromServer {
    public static void main(String[] args) {
        final int PORT = 8888;
        try(Socket socket = new Socket("localhost", PORT)){
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
