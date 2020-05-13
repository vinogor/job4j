
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        first.start(); // запуск в НОВОМ потоке
//        first.run(); // запуск в том же потоке
        second.start();

        System.out.println(Thread.currentThread().getName());
    }
}