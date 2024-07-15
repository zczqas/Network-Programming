import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RedirectClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8009;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
        ) {
            System.out.println(
                "Connected to server on " + SERVER_ADDRESS + ":" + SERVER_PORT
            );

            String filePath = "/index.html";

            out.println("GET " + filePath + " HTTP/1.1");
            out.println();

            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}
