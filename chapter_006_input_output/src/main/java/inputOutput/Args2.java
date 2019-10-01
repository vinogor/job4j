package inputOutput;

// Пример:
// -jar find.jar -d c:/ -n *.txt -m -o log.txt

// Ключи
// -d - директория в которая начинать поиск.
// -n - имя файла, маска, либо регулярное выражение.
// -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.
// -o - результат записать в файл.

import java.util.HashMap;
import java.util.Map;

public class Args2 {

    private Map<String, String> hashArgs = new HashMap<>();

    public Args2(String[] args) {
        String key = null;
        String value = null;
        boolean isKey = false;

        for (String arg : args) {
            if (!isKey && arg.startsWith("-")) {
                key = arg;
                isKey = true;
            } else if (isKey && !arg.startsWith("-")) {
                value = arg;
                hashArgs.put(key, value);
                isKey = false;
            } else if (isKey && arg.startsWith("-")) {
                hashArgs.put(key, null);
                key = arg;
            }
        }
        if (isKey) {
            hashArgs.put(key, null);
        }
    }

    public String getDir() throws Exception {
        if (!hashArgs.containsKey("-d")) {
            System.out.println("ERROR: arg -d undefined (directory for search)");
            System.out.println("    restart program with arg like \"-d c:\\directory\\\" ");
            return null;
        }
        return hashArgs.get("-d");
    }

    public String getName() throws Exception {
        if (!hashArgs.containsKey("-n")) {
            System.out.println("ERROR: arg -n undefined (file name or mask for search)");
            System.out.println("    restart program with arg like \"-n file_name.ext\" or \"-n *.ext\" ");
            return null;
        }
        return hashArgs.get("-n");
    }

    public String getTypeOfSearch() throws Exception {
        boolean m = hashArgs.containsKey("-m");
        boolean f = hashArgs.containsKey("-f");
        boolean r = hashArgs.containsKey("-r");

        if (!m && !f && !r) {
            System.out.println("ERROR: type of search undefined");
            System.out.println("    restart program with one of args below");
            System.out.println("        -m - to search by Mask");
            System.out.println("        -f - to search by Full file name");
            System.out.println("        -r - to search by Regular expression");
            return null;
        }

        if (m) {
            return "mask";
        } else if (f) {
            return "full";
        } else {
            return "regular";
        }
    }

    public String getOutput() throws Exception {
        if (!hashArgs.containsKey("-o")) {
            System.out.println("ERROR: arg -o undefined (output file name)");
            System.out.println("    restart program with arg like \"-o file_name.ext\" ");
            return null;
        }
        return hashArgs.get("-o");
    }
}