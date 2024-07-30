import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOChannelServerAndClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Start the server in a separate thread
        new Thread(() -> runServer()).start();

        // Wait a bit to ensure the server has started
        Thread.sleep(1000);

        // Run the client
        runClient();
    }

    private static void runServer() {
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(8000));
            System.out.println("Server started on port 8000");

            while (true) {
                SocketChannel clientChannel = serverSocket.accept();
                ByteBuffer buffer = ByteBuffer.allocate(256);
                clientChannel.read(buffer);
                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println("Received from client: " + new String(bytes));

                String response = "Hello from server";
                buffer.clear();
                buffer.put(response.getBytes());
                buffer.flip();
                clientChannel.write(buffer);

                clientChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runClient() {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 8000);
            SocketChannel client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(256);
            String message = "Hello world";
            buffer.put(message.getBytes());
            buffer.flip();
            client.write(buffer);

            buffer.clear();
            client.read(buffer);
            buffer.flip();

            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);

            System.out.println("Received from server: " + new String(bytes));

            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}