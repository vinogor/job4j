package LMG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deadlock2 {
    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();
    private static final Object lock = new Object();

    private static volatile boolean done = false;

    public static void main(final String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(Deadlock2::handler1));
        threads.add(new Thread(Deadlock2::handler2));

        Collections.shuffle(threads);

        threads.get(0).start();
        Thread.sleep(Math.abs(new Random().nextInt(500) + 500));
        threads.get(1).start();
    }

    private static void handler1() {

        System.out.println("    старт метода handler1() ");
        System.out.println("    метод handler1() пытается получить monitor1");

        synchronized (monitor1) {
            System.out.println("    метод handler1() получил monitor1");

            try {
                synchronized (lock) {
                    while (!done) {
                        lock.wait();
                    }
                    done = false;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("    метод handler1() пытается получить monitor2");
            synchronized (monitor2) {
                System.out.println("    метод handler1() получил monitor2");
                System.out.println("Hello from handler1");
            }
        }
        System.out.println("    завершение метода handler1() ");
    }

    private static void handler2() {
        System.out.println("    старт метода handler2() ");
        System.out.println("    метод handler2() пытается получить monitor2");
        synchronized (monitor2) {
            System.out.println("    метод handler2() получил monitor2");

            try {
                synchronized (lock) {
                    while (done) {
                        lock.wait();
                    }
                    done = true;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("    метод handler2() пытается получить monitor1");
            synchronized (monitor1) {
                System.out.println("    метод handler2() получил monitor1");
                System.out.println("Hello from handler2");
            }
        }
        System.out.println("    завершение метода handler1() ");
    }
}
