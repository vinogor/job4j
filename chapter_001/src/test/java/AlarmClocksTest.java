import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AlarmClocksTest {

	@Test
	public void timeToWakeUpTest1() {
		AlarmClocks clocks = new AlarmClocks();
		int[] nxk = new int[]{6, 5, 10};
		int[] timeToRing = new int[]{1, 2, 3, 4, 5, 6};
		int result = clocks.timeToWakeUp(nxk, timeToRing);
		int expect = 10;
		assertThat(result, is(expect));
	}

	@Test
	public void timeToWakeUpTest2() {
		AlarmClocks clocks = new AlarmClocks();
		int[] nxk = new int[] {5, 7, 12};
		int[] timeToRing = new int[]{5, 22, 17, 13, 8};
		int result = clocks.timeToWakeUp(nxk, timeToRing);
		int expect = 27;
		assertThat(result, is(expect));
	}
}