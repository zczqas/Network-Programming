package MultiThreadedChan;

public class Clients {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ClientThreads client_threads = new ClientThreads(i);
            client_threads.start();
        }
    }
}
