package exam;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    private Analize analize = new Analize();
    private List<User> previous = new ArrayList<>();
    private List<User> current = new ArrayList<>();

    private User user1;
    private User user2;
    private User user2mod;
    private User user3;
    private User user4;

    @Before
    public void setUp() {
        user1 = new User(1, "first");
        user2 = new User(2, "second");
        user2mod = new User(2, "second_mod");
        user3 = new User(3, "third");
        user4 = new User(4, "fourth");
    }

    @Test
    public void diffTest1() {
        previous.add(user1);
        previous.add(user2);
        current.add(user3);
        current.add(user4);
        Info result = analize.diff(previous, current);

        assertThat(result, is(new Info(2, 0, 2)));
    }

    @Test
    public void diffTest2() {
        previous.add(user1);
        previous.add(user2);
        current.add(user2mod);
        current.add(user3);
        current.add(user4);
        Info result = analize.diff(previous, current);

        assertThat(result, is(new Info(2, 1, 1)));
    }

    @Test
    public void diffTest3() {
        previous.add(user1);
        previous.add(user2);
        current.add(user1);
        current.add(user2mod);
        current.add(user3);
        current.add(user4);
        Info result = analize.diff(previous, current);

        assertThat(result, is(new Info(2, 1, 0)));
    }

    @Test
    public void diffTest4() {
        previous.add(user1);
        previous.add(user2);
        current.add(user1);
        current.add(user2);
        Info result = analize.diff(previous, current);

        assertThat(result, is(new Info(0, 0, 0)));
    }

    @Test
    public void diffTest5() {
        previous.add(user1);
        previous.add(user2);
        current.add(user2mod);
        Info result = analize.diff(previous, current);

        assertThat(result, is(new Info(0, 1, 1)));
    }
}