import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class NIOChannelClient {
    public static void main(String[] args) throws IOException {
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