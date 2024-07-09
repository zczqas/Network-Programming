package MultiThreadedChan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int PORT = 13001;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket conn = server.accept();
                    Thread task = new ServerThreads(conn);
                    System.out.println("connection started for port: " + conn.getPort());
                    task.start();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println("could not listen on port " + PORT);
            System.out.println(e.getMessage());
        }
    }
}
