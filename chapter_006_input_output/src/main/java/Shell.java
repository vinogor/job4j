import java.util.Arrays;

public class Shell {

    private StringBuilder sbPath = new StringBuilder();

    Shell cd(final String path) {

        String[] split = path.split("/");
        System.out.print("пришла команда cd: ");
        System.out.println(Arrays.toString(split));


        for (String str : split) {
            if (str.matches("^[a-zA-Z0-9]+$")) {
                sbPath.append("/").append(str);
                System.out.println("    переходим в директорию " + str);
            } else {
                switch (str) {
                    case "..":
                        sbPath.delete(sbPath.lastIndexOf("/"), sbPath.length());
                        System.out.println("    поднимаемся на уровень вверх");
                        break;
                    case ".":
                        System.out.println("    остаёмся на месте");
                        break;
                    case "":
                        System.out.println("    пустая команда");
                        break;
                    default:
                        System.out.println("    неопознанная команда");
                        break;
                }
            }
        }
        System.out.println("    итоговый путь " + path());
        return this;
    }

    public String path() {
        return this.sbPath.length() == 0 ? "/" : this.sbPath.toString();
    }
}
