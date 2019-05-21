package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 21.05.2019
 */
public class CalculateTest {
   
   @Test
	public void whenSetStopInEchoThenReturnThreeStops() {
		Calculate calc = new Calculate();
		String result = calc.echo("stop");
		assertThat(result, is("stop stop stop"));
	}

   @Test
	public void whenSetNullInEchoThenReturnTwoSpaces() {
		Calculate calc = new Calculate();
		String result = calc.echo(null);
		assertThat(result, is("null null null"));
	}		
}