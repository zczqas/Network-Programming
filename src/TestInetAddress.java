//Using InetAddress
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    public static void main(String[] args) {
        try {
            //Loop-back Address
//            InetAddress ip = InetAddress.getByName("127.0.0.1");
//            InetAddress ip = InetAddress.getByName("FF02:0:0:0:0:0:0:1");
            //MCGlobal 224 to 240
//            InetAddress ip = InetAddress.getByName("230.0.0.1");
            //Local address
            InetAddress ip = InetAddress.getByName("0.0.0.0");
            System.out.println("Host Address: " + ip.getHostAddress());
            System.out.println("Host Name: " + ip.getHostName());
            System.out.println("IP Address Length: " + ip.getAddress().length);

            byte[] ipBytes = ip.getAddress();
            StringBuilder ipAddressStr = new StringBuilder();
            for (byte b : ipBytes) {
                ipAddressStr.append((b & 0xFF)).append('.');
            }
            ipAddressStr.deleteCharAt(ipAddressStr.length() - 1);
            System.out.println("IP Address: " + ipAddressStr.toString());

            System.out.println("Canonical Address: " + ip.getCanonicalHostName());
            System.out.println("Global: " + ip.isMCGlobal());
            System.out.println("Loop-back: " + ip.isLoopbackAddress());
            System.out.println("LinkLocal: " + ip.isAnyLocalAddress());
        }catch (UnknownHostException e) {
            System.out.println("Unknown host" + e.getMessage());
        }catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}