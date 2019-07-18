import java.util.*;
import java.util.regex.Pattern;

public class DepartmentsSort2 {

    private List<String> departments;
    private List<List<String>> separatedDepartments;
    private int maxNumberOfNames;

    public DepartmentsSort2(List<String> departments) {
        this.departments = departments;
    }

    private void separate() {
        separatedDepartments = new ArrayList<>();
        // определяем максимально возможное кол-во имён в департаменте
        maxNumberOfNames = 0;
        for (String department : departments) {
            int currentNumberOfNames = department.split(Pattern.quote("\\")).length;
            if (currentNumberOfNames > maxNumberOfNames) {
                maxNumberOfNames = currentNumberOfNames;
            }
        }
        // разбиваем и заполняем структуру с которой будем работать в дальнейшем
        for (String department : departments) {
            String[] parts = new String[maxNumberOfNames];
            Arrays.fill(parts, ""); // чтобы не обрабатывать случаи сравнения с null
            String[] split = department.split(Pattern.quote("\\"));
            System.arraycopy(split, 0, parts, 0, split.length);
            separatedDepartments.add(Arrays.asList(parts));
        }
    }

    List<String> sortAsc() {
        separate();
        separatedDepartments.sort((o1, o2) -> {
            int result = 0;
            for (int i = 0; i < maxNumberOfNames; i++) {
                result = o1.get(i).compareTo(o2.get(i));
                if (result != 0) {
                    break;
                }
            }
            return result;
        });
        return convert(separatedDepartments);
    }

    List<String> sortDesc() {
        separate();
        separatedDepartments.sort((o1, o2) -> {
            int result = 0;
            for (int i = 0; i < maxNumberOfNames; i++) {
                if (o2.get(i).equals("")) {
                    result = 1;
                    break;
                } else if (o1.get(i).equals("")) {
                    result = -1;
                    break;
                } else {
                    result = o2.get(i).compareTo(o1.get(i));
                    if (result != 0) {
                        break;
                    }
                }
            }
            return result;
        });
        return convert(separatedDepartments);
    }

    private List<String> convert(List<List<String>> separatedDepartments) {
        List<String> result = new ArrayList<>();
        for (List<String> department : separatedDepartments) {
            StringBuilder namesOfDep = new StringBuilder();

            namesOfDep.append(department.get(0));
            for (int i = 1; i < maxNumberOfNames; i++) {
                if (!(department.get(i).equals(""))) {
                    namesOfDep.append("\\");
                    namesOfDep.append(department.get(i));
                }
            }
            result.add(namesOfDep.toString());
        }
        return result;
    }

    void print() {
        for (List<String> list : separatedDepartments) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
