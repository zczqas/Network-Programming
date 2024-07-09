import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ProcessLog {
    public static void main(String[] args) {
        String file = "logfile.txt";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            for (String entry = br.readLine(); entry != null; entry = br.readLine()) {
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String the_rest = entry.substring(index);

                InetAddress address = InetAddress.getByName(ip);
                System.out.println(address.getHostName() + the_rest);
            }
            br.close();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
