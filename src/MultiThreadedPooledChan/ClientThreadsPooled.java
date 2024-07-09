package MultiThreadedPooledChan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThreadsPooled extends Thread{
    private int no;

    public ClientThreadsPooled(int no){
        this.no = no;
    }

    @Override
    public void run() {
        Socket server = null;

        try {
            server = new Socket("localhost", 13001);
            System.out.println("connection started for port: " + server.getPort());

            InputStreamReader in = new InputStreamReader(server.getInputStream());
            BufferedReader input = new BufferedReader(in);
            String message = input.readLine();
            System.out.println("message from server: " + message);

        }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }
            }
        }
    }
}
