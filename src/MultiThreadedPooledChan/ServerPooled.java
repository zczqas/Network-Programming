package MultiThreadedPooledChan;

import MultiThreadedChan.ClientThreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPooled {
    public final static int PORT = 13001;
    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);
            while (true) {
                try {
                    Socket conn = server.accept();
                    System.out.println("New connection from " + conn.getRemoteSocketAddress());

                    ServerThreadsPooled clientThreads = new ServerThreadsPooled(conn);
                    pool.submit(clientThreads);

                    Thread.sleep(5000);
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("General Error: " + e.getMessage());
                }
            }
        }catch (IOException e) {
            System.out.println("could not listen on port " + PORT);
            System.out.println(e.getMessage());
        }
    }
}
