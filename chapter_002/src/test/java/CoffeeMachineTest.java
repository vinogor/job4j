import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CoffeeMachineTest {

    @Test
    public void changesTest1() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(50, 35);
        int[] expect = new int[]{10, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void changesTest2() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(54, 35);
        int[] expect = new int[]{10, 5, 2, 2};
        assertThat(result, is(expect));
    }

    @Test
    public void changesTest3() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(53, 35);
        int[] expect = new int[]{10, 5, 2, 1};
        assertThat(result, is(expect));
    }

    @Test
    public void changesTest4() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(50, 50);
        int[] expect = new int[]{};
        assertThat(result, is(expect));
    }
}