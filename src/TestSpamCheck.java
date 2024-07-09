import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestSpamCheck {
    public static final String blackhole = "sbl.spamhaus.org";

    private static boolean isSpam(String addr) {
        try {
            String query = blackhole;
            InetAddress ip = InetAddress.getByName(addr);

            byte[] bytes = ip.getAddress();
            for (byte b : bytes) {
                int a = b < 0 ? 256 + b : b;
                query = a + "." + query;
//                System.out.println(query);
            }

            InetAddress inet = InetAddress.getByName(query);
            System.out.println("Host address: " + inet.getHostAddress());
            return true;
        }catch (UnknownHostException e) {
//            System.out.println("Error: " + e.getMessage());
            return false;
        }

//        return false;
    }
    public static void main(String[] args) {
        String[] addresses = {"192.168.0.1", "0.0.0.0", "185.196.8.151"};

        for (String addr : addresses) {
            boolean isSpam = isSpam(addr);
            System.out.println(addr + " IsSpam: " + isSpam);
        }
    }
}
