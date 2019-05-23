package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] input = new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] result = arrayDuplicate.remove(input);
		String[] expect = new String[]{"Привет", "Мир", "Супер"};
		assertThat(result, is(expect));
	}

	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate2() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] input = new String[]{"Привет", "Привет", "Привет", "Привет"};
		String[] result = arrayDuplicate.remove(input);
		String[] expect = new String[]{"Привет"};
		assertThat(result, is(expect));
	}

	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate3() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] input = new String[]{"Привет", "Мир", "Супер"};
		String[] result = arrayDuplicate.remove(input);
		String[] expect = new String[]{"Привет", "Мир", "Супер"};
		assertThat(result, is(expect));
	}
}