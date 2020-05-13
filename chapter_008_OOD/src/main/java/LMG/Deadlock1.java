package LMG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Deadlock1 {
    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();
    private static final CountDownLatch cdl = new CountDownLatch(2);

    public static void main(final String[] args) {
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(Deadlock1::handler1));
        threads.add(new Thread(Deadlock1::handler2));

        Collections.shuffle(threads);

        System.out.println("cdl = " + cdl.getCount());
        threads.get(0).start();
        threads.get(1).start();
    }

    private static void handler1() {
        System.out.println("    старт метода handler1() ");
        System.out.println("    метод handler1() пытается получить monitor1");

        synchronized (monitor1) {
            System.out.println("    метод handler1() получил monitor1");
            try {
                cdl.countDown();
                System.out.println("метод handler1() изменил cdl = " + cdl.getCount());

                System.out.println("    метод handler1() засыпает/ожидает");
                cdl.await();
                System.out.println("    метод handler1() просыпается");
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
                cdl.countDown();
                System.out.println("метод handler2() изменил cdl = " + cdl.getCount());

                System.out.println("    метод handler2() засыпает/ожидает");
                cdl.await();
                System.out.println("    метод handler2() просыпается");
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
