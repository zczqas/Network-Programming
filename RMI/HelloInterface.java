// STEP #1: Create the remote interface
import java.rmi.Remote;

public interface HelloInterface extends Remote {
    public String say() throws java.rmi.RemoteException;
    public String add(int a, int b) throws java.rmi.RemoteException;
    public String subtract(int a, int b) throws java.rmi.RemoteException;
}
