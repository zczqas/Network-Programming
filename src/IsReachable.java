import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IsReachable {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("0.0.0.0");
            InetAddress ip2 = InetAddress.getByName("google.com");

            try {
               boolean is_ip_reachable = ip.isReachable(2);
               System.out.println(is_ip_reachable);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            try {
                boolean is_google_reachable = ip2.isReachable(2);
                System.out.println(is_google_reachable);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }catch(UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
