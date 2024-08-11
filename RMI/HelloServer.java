// STEP #3: Create the server class
import java.rmi.Naming;

public class HelloServer {
    public static void main(String[] args) {
        try {
            Hello remoteObject = new Hello("connecting to the server");
            // Bind the remote object's stub in the registry
            Naming.rebind("Hello", remoteObject);
            System.out.println("HelloServer bound in registry");
        } catch (Exception e) {
            System.out.println("HelloServer error: " + e.getMessage());
        }
    }
}
