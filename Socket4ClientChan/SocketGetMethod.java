import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketGetMethod {
    public static void main(String[] args) {
        String host = "time.nist.gov";
        int port = 13;
        Socket socket = null;

        try {
            socket = new Socket();

            // Connect to the socket using InetAddress
            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address);

            System.out.println("Server InetAddress: " + socket.getInetAddress());
            System.out.println("Server InetAddress with port: " + socket.getRemoteSocketAddress());
            System.out.println("Server Port: " + socket.getPort());
            System.out.println("Local Address: " + socket.getLocalAddress());
            System.out.println("Local port: " + socket.getLocalPort());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
