package inputOutput;

public class Args {
    private String dir;
    private String exc;
    private String out;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    dir = args[++i];
                    break;
                case "-e":
                    exc = args[++i];
                    break;
                case "-o":
                    out = args[++i];
                    break;
            }
        }
    }
    public String directory() {
        return dir;
    }
    public String exclude () {
        return exc;
    }
    public String output() {
        return out;
    }
}