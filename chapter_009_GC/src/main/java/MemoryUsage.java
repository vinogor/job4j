// VM options:
// -Xmx24m

public class MemoryUsage {

    public static class User {
         public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("GC coming...");
            super.finalize();
        }
    }
    public static void main(String[] args) {
        System.out.println("start");
        info();
        for (int i = 0; i < 150_00000; i++) {
            User user = new User("test");
        }
        System.out.println("end");
        info();
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
