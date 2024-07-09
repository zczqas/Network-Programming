import java.net.ConnectException;
import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        Socket socket = null;
        String host = "localhost";
        for (int port = 1; port <= 65535; port++) {
            try {
                socket = new Socket(host, port);
                System.out.println("There is a server on port " + port);
            }catch (ConnectException e){
                // DO NOTHING
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
}
