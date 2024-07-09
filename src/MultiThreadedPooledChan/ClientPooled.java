package MultiThreadedPooledChan;


public class ClientPooled {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ClientThreadsPooled client_threads = new ClientThreadsPooled(i);
            client_threads.start();
        }
    }
}
