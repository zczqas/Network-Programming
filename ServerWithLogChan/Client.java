import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 20000;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to server on " + SERVER_ADDRESS + ":" + SERVER_PORT);

            System.out.print("Enter a message to send to the server: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                return;
            }
            int message = Integer.parseInt(scanner.nextLine());

            out.println(message);
            System.out.println("Message sent to server: " + message);

            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}