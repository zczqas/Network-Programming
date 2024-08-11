// STEP #2: Create the remote object
import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements HelloInterface {
    private final String message;

    public Hello(String msg) throws java.rmi.RemoteException {
        message = msg;
    }

    public String say() throws java.rmi.RemoteException {
        return message;
    }

    public String add(int a, int b) throws java.rmi.RemoteException {
        int result = a + b;
        return "The sum of " + a + " and " + b + " is " + result;
    }

    public String subtract(int a, int b) throws java.rmi.RemoteException {
        int result = a - b;
        return "The difference of " + a + " and " + b + " is " + result;
    }
}
