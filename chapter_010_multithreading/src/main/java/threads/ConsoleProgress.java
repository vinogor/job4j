package threads;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r loading: -");
                Thread.sleep(500);
                System.out.print("\r loading: \\");
                Thread.sleep(500);
                System.out.print("\r loading: |");
                Thread.sleep(500);
                System.out.print("\r loading: /");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
