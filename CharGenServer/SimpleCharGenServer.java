import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SimpleCharGenServer {
    public static void main(String[] args) {
        final int PORT = 8888;
        byte[] rotation = new byte[95 * 2];
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("CharGen server started at port: " + PORT);

            while (true) {
                try(Socket clientSocket = serverSocket.accept()){
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    OutputStream out = clientSocket.getOutputStream();
                    ByteBuffer buffer = ByteBuffer.allocate(74);
                    int pos = 0;

                    while (true) {
                        buffer.clear();
                        buffer.put(rotation, pos, 72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');

                        buffer.flip();
                        out.write(buffer.array(), 0, buffer.limit());

                        ++pos;
                        if (pos == 95)
                            pos = 0;
                    }
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
