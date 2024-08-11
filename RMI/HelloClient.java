// STEP #4: Create the client class
public class HelloClient {
    public static void main(String[] args) {
        try {
            HelloInterface hello = (HelloInterface) java.rmi.Naming.lookup("rmi://localhost/Hello");
            System.out.println(hello.say());
            System.out.println(hello.add(4, 3));
            System.out.println(hello.subtract(4, 3));
        }catch (Exception e) {
            System.out.println("HelloClient exception: " + e.getMessage());
        }
    }
}
