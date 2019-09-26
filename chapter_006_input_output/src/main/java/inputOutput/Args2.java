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
            throw new Exception("arg -d undefined");
        }
        return hashArgs.get("-d");
    }

    public String getName() throws Exception {
        if (!hashArgs.containsKey("-n")) {
            throw new Exception("arg -n undefined");
        }
        return hashArgs.get("-n");
    }

    public String getTypeOfSearch() throws Exception {
        boolean m = hashArgs.containsKey("-m");
        boolean f = hashArgs.containsKey("-f");
        boolean r = hashArgs.containsKey("-r");

        if (!m && !f && !r) {
            throw new Exception("type of search undefined");
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
            throw new Exception("arg -o undefined");
        }
        return hashArgs.get("-o");
    }
}