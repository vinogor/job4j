package list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckCycleTest {

    private CheckCycle checkCycle;

    @Before
    public void setUp() {
        checkCycle = new CheckCycle();
    }

    // кольцо есть, из first оно достигается, проход по всем элементам
    @Test
    public void hasCycleTest1() {
        checkCycle.setNodsNext(
            checkCycle.two,
            checkCycle.third,
            checkCycle.four,
            checkCycle.first
        );
        assertThat(checkCycle.hasCycle(checkCycle.first), is(true));
    }

    // кольцо есть, из first оно НЕ достигается
    @Test
    public void hasCycleTest2() {
        checkCycle.setNodsNext(
            checkCycle.two,
            null,
            checkCycle.four,
            checkCycle.third
        );
        assertThat(checkCycle.hasCycle(checkCycle.first), is(false));
    }

    // кольца НЕТ вообще
    @Test
    public void hasCycleTest3() {

        checkCycle.setNodsNext(
            checkCycle.two,
            checkCycle.third,
            checkCycle.four,
            null
        );
        assertThat(checkCycle.hasCycle(checkCycle.first), is(false));
    }

    // кольцо есть, из first оно достигается, проход НЕ по всем элементам
    @Test
    public void hasCycleTest4() {
        checkCycle.setNodsNext(
            checkCycle.two,
            checkCycle.third,
            checkCycle.first,
            null
        );
        assertThat(checkCycle.hasCycle(checkCycle.first), is(true));
    }

    // first сразу указывает на null
    @Test
    public void hasCycleTest5() {
        checkCycle.setNodsNext(
            null,
            checkCycle.third,
            checkCycle.four,
            checkCycle.first
        );
        assertThat(checkCycle.hasCycle(checkCycle.first), is(false));
    }
}