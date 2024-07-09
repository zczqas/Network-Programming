import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class SingleFileServer {
    public static Logger infoLogger = Logger.getLogger("requests");
    public static Logger errorLogger = Logger.getLogger("errors");

    public static byte[] content;
    public static byte[] header;
    public int port;
    public String encoding;

    public SingleFileServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port
        );
    }

    public SingleFileServer(
            byte[] data,
            String encoding,
            String mimeType,
            int port
    ) {
        this.content = data;
        this.encoding = encoding;
        this.port = port;
        String header = "HTTP/1.1 200 OK\r\n" +
                "Server: OneFile 2.0\\r\n" +
                "Content-length: " + this.content.length + "\r\n" +
                "Content-type: " + mimeType + "; charset=" + encoding + "\r\n\r\n";

        SingleFileServer.header = header.getBytes(StandardCharsets.US_ASCII);
    }

    public static void main(String[] args) {
        int port = 9001;
        String file = "SingleFileHTTP/index.html";
        String encoding = "UTF-8";

        try {
            Path path = Paths.get(file);
            byte[] data = Files.readAllBytes(path);

            String contentType = URLConnection.getFileNameMap().getContentTypeFor(path.toString());

            new SingleFileServer(data, encoding, contentType, port);
        } catch (IOException e) {
            errorLogger.severe("Cannot read file: " + e.getMessage());
        }

        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    OutputStream out = new BufferedOutputStream(
                            connection.getOutputStream()
                    );
                    InputStream input = new BufferedInputStream(
                            connection.getInputStream()
                    );

                    StringBuilder request = new StringBuilder(80);
                    while (true) {
                        int c = input.read();
                        if (c == '\r' || c == '\n' || c == -1) break;
                        request.append((char) c);
                    }

                    if (request.toString().indexOf("HTTP/") != -1) {
                        out.write(header);
                    }

                    out.write(content);

                    out.flush();
                } catch (IOException e) {
                    errorLogger.severe("cannot open connection: " + e.getMessage());
                }
            }
        } catch (
                IOException e) {
            errorLogger.severe("Could not start server: " + e.getMessage());
        }
    }

}