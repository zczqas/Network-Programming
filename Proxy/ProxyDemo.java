import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class ProxyDemo {

    public static void main(String[] args) {
        System.setProperty("http.proxyHost", "3.96.92.88");
        System.setProperty("http.proxyPort", "3128");

        try {
            validateProxyServer("3.96.92.88", 3128);
            URL url = new URL("http://example.com");
            System.out.println(url);

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("3.96.92.88", 3128));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);

            conn.setRequestMethod("GET");

            InputStream input = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println(response);

            conn.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateProxyServer(String proxyHost, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(proxyHost, port), 10000);
            System.out.println("is reachable");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}