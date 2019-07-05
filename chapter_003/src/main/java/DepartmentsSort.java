import java.util.*;
import java.util.regex.Pattern;

public class DepartmentsSort {

    class Department {
        String lev1;
        String lev2;
        String lev3;

        public Department(String lev1, String lev2, String lev3) {
            this.lev1 = lev1;
            this.lev2 = lev2;
            this.lev3 = lev3;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder(lev1);
            if (!lev2.equals("")) {
                res.append("\\").append(lev2);
            }
            if (!lev3.equals("")) {
                res.append("\\").append(lev3);
            }
            return res.toString();
        }
    }

    public List<String> sortAsc(List<String> dep) {

        List<Department> res = fill(dep);

        res.sort((o1, o2) -> {
            int result = o1.lev1.compareTo(o2.lev1);

            if (result == 0) {
                result = customCompare(o1.lev2, o2.lev2, 1);
                if (result == 0) {
                    result = customCompare(o1.lev3, o2.lev3, 1);
                }
            }
            return result;
        });
        return convert(res);
    }

    public List<String> sortDes(List<String> dep) {

        List<Department> res = fill(dep);

        res.sort((o1, o2) -> {
            int result = o2.lev1.compareTo(o1.lev1);

            if (result == 0) {
                result = customCompare(o2.lev2, o1.lev2, -1);
                if (result == 0) {
                    result = customCompare(o2.lev3, o1.lev3, -1);
                }
            }
            return result;
        });
        return convert(res);
    }

    private List<Department> fill(List<String> dep) {
        List<Department> preRes = new ArrayList<>();
        for (String s : dep) {
            String[] parts = new String[]{"", "", ""};
            String[] split = s.split(Pattern.quote("\\"));
            System.arraycopy(split, 0, parts, 0, split.length);
            preRes.add(new Department(parts[0], parts[1], parts[2]));
        }
        return preRes;
    }

    private int customCompare(String s1, String s2, int i) {
        int result;
        if (s2.equals("")) {
            result = i;
        } else if (s1.equals("")) {
            result = -i;
        } else {
            result = s1.compareTo(s2);
        }
        return result;
    }

    private List<String> convert(List<Department> res) {
        List<String> result = new ArrayList<>();
        for (Department department : res) {
            result.add(department.toString());
        }
        return result;
    }
}