import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Scanner;

public class SecureOrderTaker {
    public final static int PORT = 7000;
    public final static String algorithm = "SSL";

    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");

            // Use Scanner to read password
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter keystore password: ");
            String passwordStr = scanner.nextLine();
            char[] password = passwordStr.toCharArray();

            ks.load(new FileInputStream("SecureSocket/nwjens.keys"), password);
            kmf.init(ks, password);

            context.init(kmf.getKeyManagers(), null, null);
            Arrays.fill(password, '0');

            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);

            String[] supported = server.getSupportedCipherSuites();
            String[] anonCipherSuitesSupported = new String[supported.length];
            int numAnonCipherSuitesSupported = 0;

            for (String s : supported) {
                if (s.indexOf("_anon_") > 0) {
                    anonCipherSuitesSupported[numAnonCipherSuitesSupported++] = s;
                }
            }

            String[] oldEnabled = server.getEnabledCipherSuites();
            System.out.println("Enabled cipher suites:");
            System.out.println(oldEnabled.length);
            String[] newEnabled = new String[
                    oldEnabled.length +
                            numAnonCipherSuitesSupported
                    ];

            System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
            System.arraycopy(anonCipherSuitesSupported, 0, newEnabled, oldEnabled.length, numAnonCipherSuitesSupported);

            server.setEnabledCipherSuites(newEnabled);

            while (true) {
                try(Socket theConnection = server.accept()) {
                    InputStream in = theConnection.getInputStream();
                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.write(c);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}