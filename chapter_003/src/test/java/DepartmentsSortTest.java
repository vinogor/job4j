import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsSortTest {

    DepartmentsSort2 departmentsSort;
    List<String> departments;

    @Before
    public void setUp() {
        departments = new ArrayList<>();
        departments.add("K1");
        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K1");
        departments.add("K1\\SK1\\SSK2");
        departments.add("K1\\SK1\\SSK1");
        departments.add("K1\\SK2\\SSK1");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2\\SK1");
        departments.add("K2\\SK2");
        departments.add("K2\\SK2\\SSK2");
        departments.add("K2\\SK2\\SSK1");
        departments.add("K2");
        departmentsSort = new DepartmentsSort2(departments);

    }

    @Test
    public void sortAscTest() {
        List<String> result = departmentsSort.sortAsc();
        assertThat(result, is(List.of(
            "K1",
            "K1",
            "K1\\SK1",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K1\\SK2",
            "K1\\SK2\\SSK1",
            "K2",
            "K2\\SK1",
            "K2\\SK1\\SSK1",
            "K2\\SK2",
            "K2\\SK2\\SSK1",
            "K2\\SK2\\SSK2"
        )));
    }

    @Test
    public void sortDesTest() {
        List<String> result = departmentsSort.sortDesc();
        assertThat(result, is(List.of(
            "K2",
            "K2\\SK2",
            "K2\\SK2\\SSK2",
            "K2\\SK2\\SSK1",
            "K2\\SK1",
            "K2\\SK1\\SSK1",
            "K1",
            "K1",
            "K1\\SK2",
            "K1\\SK2\\SSK1",
            "K1\\SK1",
            "K1\\SK1\\SSK2",
            "K1\\SK1\\SSK1"
        )));
    }

}