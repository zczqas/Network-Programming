import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.*;

public class RedirectServer {

    private static final int port = 8009;
    private static final String newSite = "new-resource-path";
    private static final Logger logger = Logger.getLogger(
        RedirectServer.class.getName()
    );

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info(
                "Redirecting connections on port " +
                server.getLocalPort() +
                " to " +
                newSite
            );

            while (true) {
                Socket socket = server.accept();
                handleRequest(socket);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "SEVERE error: ", e);
        }
    }

    private static void handleRequest(Socket socket) {
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            final String input = reader.readLine();
            logger.info("Request received: " + input);

            if (input != null && !input.isEmpty()) {
                String[] pieces = input.split("\\s+");
                if (pieces.length > 1) {
                    String theFile = pieces[1];

                    System.out.println(Arrays.toString(pieces));
                    System.out.println(theFile);

                    if (input.contains("HTTP")) {
                        sendHttpResponse(out, theFile);
                    } else {
                        logger.warning("unsupported request: " + input);
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error writing response", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "error closing socket", e);
            }
        }
    }

    private static void sendHttpResponse(PrintWriter out, String theFile) {
        out.println("HTTP/1.1 302 Found");
        Date now = new Date();
        out.println("Date: " + now);
        out.println("Server: Redirect 1.0");
        out.println("Location: " + newSite + theFile);
        out.println("Content-type: text/html");
        out.println();

        out.println("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>");
        out.println("<BODY><H1>Document moved</H1>");
        out.println("The document " + theFile + " has moved to");
        out.println(
            "<A HREF=\"" +
            newSite +
            theFile +
            "\">" +
            newSite +
            theFile +
            "</A>."
        );
        out.println("Please update your bookmarks");
        out.println("</BODY></HTML>");

        out.flush();
    }
}
