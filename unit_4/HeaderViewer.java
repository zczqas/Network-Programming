import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HeaderViewer {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com/");
            URLConnection conn = url.openConnection();
            System.out.println("Content-Type: " + conn.getContentType());

            String contentType = conn.getHeaderField("Content-Type");
            System.out.println("Content-Type(getHeaderField): " + contentType);

            if (conn.getContentLength() != -1) {
                System.out.println("Content-Length: " + conn.getContentLength());
            }
            String contentLength = conn.getHeaderField("Content-Length");
            System.out.println("Content-Length(getHeaderField): " + contentLength);

            if (conn.getContentEncoding() != null) {
                System.out.println("Content-Encoding: " + conn.getContentEncoding());
            }

            if (conn.getDate() != 0) {
                System.out.println("Date: " + new Date(conn.getDate()));
            }

            if (conn.getLastModified() != 0) {
                System.out.println("Last-Modified: " + new Date(conn.getLastModified()));
            }

            if (conn.getExpiration() != 0) {
                System.out.println("Expires: " + new Date(conn.getExpiration()));
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
    }
}}
