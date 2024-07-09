import java.net.InetAddress;
import java.net.UnknownHostException;

public class ObjectMethod {
    public static void main(String[] args) {
        try {
            InetAddress ip1 = InetAddress.getByName("rojgari.com");
            InetAddress ip2 = InetAddress.getByName("api.rojgari.com");

//            InetAddress ip1 = InetAddress.getByName("youtube.com");
//            InetAddress ip2 = InetAddress.getByName("google.com");


            if (ip1.equals(ip2)) {
                System.out.println("both are equal");
            } else {
                System.out.println("Both are not same");
            }

            System.out.println(ip1.toString());
            System.out.println(ip2.toString());

            System.out.println("ip1 hashCode: " + ip1.hashCode());
            System.out.println("ip2 hashCode: " + ip2.hashCode());
        }catch(UnknownHostException e){
            System.out.println(e.getMessage());
        }
    }
}
