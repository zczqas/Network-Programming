import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static Logger infoLogger = Logger.getLogger("requests");
    public static Logger errorLogger = Logger.getLogger("errors");
    private static final int PORT = 20000;


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            errorLogger.severe(Level.SEVERE + " server socket creation failed [" + e.getMessage() + "]");
        }
        assert serverSocket != null;
        infoLogger.info("Server started on port " + serverSocket.getLocalPort());
//        Alternatively, you can use the following code to log:
//        errorLogger.info(Level.INFO + "Server started on port " + PORT);

        try {
            Socket socket;
            socket = serverSocket.accept();
            infoLogger.info("CONNECTED TO" + " " + socket.getInetAddress() + ":" + socket.getPort());

            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(input);
            int message = Integer.parseInt(reader.readLine());
            infoLogger.info("RECEIVED: " + message);

            String result;
            if (message % 2 == 0 ){
                result = "even";
            } else {
                result = "odd";
            }

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Echo: " + result);
            infoLogger.info("SENT: " + result);
        } catch (IOException e) {
            errorLogger.severe(Level.SEVERE + "cannot establish connection [" + e.getMessage() + "]");
        } catch (Exception e) {
            errorLogger.severe(Level.SEVERE + "unknown exception occurred [" + e.getMessage() + "]");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                errorLogger.severe(Level.SEVERE + "cannot close server socket [" + e.getMessage() + "]");
            }
        }
    }
}
