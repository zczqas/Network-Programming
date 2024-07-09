import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.*;
import java.util.Date;

public class DatetimeServer {
    public final static int PORT = 13001;

    public static void main(String[] args) {
        Socket conn = null;
        ServerSocket server = null;
        try {
            // Create a server socket
            server = new ServerSocket(PORT);
            conn = server.accept();
            System.out.println("Server started on port " + PORT);
            System.out.println("Connection from " + conn.getInetAddress() + " port " + conn.getPort());

            // Write the date to the client
            Writer out = new OutputStreamWriter(conn.getOutputStream());
            Date now = new Date();
            out.write("form local server: " + now.toString() + "\r\n");
            out.flush();
        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
