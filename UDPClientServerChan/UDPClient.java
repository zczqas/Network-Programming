import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        DatagramSocket clientSocket = null;

        try (Scanner scanner = new Scanner(System.in)) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.println("Enter the message to send to the server: ");
            String message = scanner.nextLine();

            clientSocket = new DatagramSocket(0);
            clientSocket.setSoTimeout(10000);

            InetAddress IPAddress = InetAddress.getByName("localhost");

            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                IPAddress,
                9876
            );
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(
                receiveData,
                receiveData.length
            );
            clientSocket.receive(receivePacket);

            String modifiesMessage = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength(),
                "UTF-8"
            );
            System.out.println("From Server: " + modifiesMessage);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }
    }
}
