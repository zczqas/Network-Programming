import java.nio.ByteBuffer;

public class Buffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(7);

        System.out.println("before FILLING: ");
        printBufferState(buffer);

        String str = "hello";
        byte[] data = str.getBytes();
        buffer.put(data);

        System.out.println("after FILLING: ");
        printBufferState(buffer);

        buffer.flip();
        System.out.println("after FLIPPING: ");
        printBufferState(buffer);

        int limit = buffer.limit();
        byte[] output = new byte[limit];
        buffer.get(output);

        System.out.println(new String(output));
        System.out.println("after GETTING: ");
        printBufferState(buffer);

        buffer.rewind();

        buffer.get(output, 1, 2);
        System.out.println("after GETTING 2 bytes: ");
        printBufferState(buffer);
        System.out.println(new String(output));

        buffer.mark();
        System.out.println("after MARKING: ");
        printBufferState(buffer);

        System.out.println(new String(output));
        System.out.println("after GETTING 2 bytes: ");
        printBufferState(buffer);

        buffer.reset();
        System.out.println("after RESETTING: ");
        printBufferState(buffer);
    }

    private static void printBufferState(ByteBuffer buffer) {
        System.out.println("Position: " + buffer.position() + " Limit: " + buffer.limit() + " Capacity: " + buffer.capacity());
    }
}
