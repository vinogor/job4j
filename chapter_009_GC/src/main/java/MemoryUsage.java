// VM options:

// == управляем памятью
// -Xmx24m

// == выбираем вид GC
// для цикла в 150_000 объектов
// -XX:+UseSerialGC        - GC был вызван: 3899 раз
// -XX:+UseParallelGC      - GC был вызван: 2098 раз
// -XX:+UseParallelOldGC   - GC был вызван: 14281 раз
// -XX:+UseConcMarkSweepGC - GC был вызван: 19654 раз   - deprecated (OpenJDK)

public class MemoryUsage {

    private static int i;

    public static class User {
         public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
//            System.out.println("GC coming...");
            i++;
            super.finalize();
        }
    }
    public static void main(String[] args) {
        System.out.println("start");
        info();
        for (int i = 0; i < 150_000; i++) {
            User user = new User("test");
        }
        System.out.println("end");
        info();
        System.out.println("GC был вызван: " + i + " раз");
    }

    public static void info() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();

        System.out.println("    #### Heap init statistics [Mb] ####");
        System.out.println("    Used memory:  " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("    Free memory:  " + runtime.freeMemory() / mb);
        System.out.println("    Total memory: " + runtime.totalMemory() / mb);
        System.out.println("    Max memory:   " + runtime.maxMemory() / mb);
        System.out.println("    ####################################");
    }
}
