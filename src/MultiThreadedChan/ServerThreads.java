package MultiThreadedChan;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;

public class ServerThreads extends Thread{
    private final Socket conn;

    ServerThreads(Socket conn){
        this.conn = conn;
        System.out.println("connection started for port: " + this.conn.getPort());
    }

    @Override
    public void run() {
        try {
            System.out.println("processing for client with port: " + this.conn.getPort());
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            System.out.println("hello from server");
            Date now = new Date();
            System.out.println(now);
            out.println(now);
        }catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }
}
