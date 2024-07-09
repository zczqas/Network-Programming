import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ReachableNetworkInterface {
    public static void main(String[] args) throws UnknownHostException {
        try {
            InetAddress ip = InetAddress.getByName("0.0.0.0");
            NetworkInterface ni = NetworkInterface.getByName("lo");

            if (ni == null) {
                System.out.println("No network interface found");
            }
            System.out.println(ni.getName());

            boolean ip_reachable = ip.isReachable(ni, 2, 5);
            System.out.println(ip_reachable);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
