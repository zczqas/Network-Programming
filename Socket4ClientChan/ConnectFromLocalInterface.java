import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectFromLocalInterface {
    public static void main(String[] args) {
        try {
            InetAddress url = InetAddress.getByName("time.nist.gov");

            InetAddress localInterface = InetAddress.getByName("192.168.16.153");

            Socket socket = new Socket(url, 13, localInterface, 0);
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            for (int i = in.read(); i != -1; i = in.read()) {
                time.append((char) i);
            }
            System.out.println(time);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
