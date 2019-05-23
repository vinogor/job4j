package ru.job4j.array;

import java.util.Arrays;

/**
 * Array duplicate remover.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class ArrayDuplicate {

	/**
	 * Main calculations.
	 *
	 * @param array input array.
	 * @return array without duplicates.
	 */
	public String[] remove(String[] array) {
		int numOfDuplicates = 0;
		int length = array.length;
		for (int i = 0; i < length - 1 - numOfDuplicates; i++) {
			for (int j = i + 1; j < length - numOfDuplicates; j++) {
				if (array[i].equals(array[j])) {
					String tmp = array[j];
					numOfDuplicates++;
					array[j] = array[length - numOfDuplicates];
					array[length - numOfDuplicates] = tmp;
					j--;
				}
			}
		}
		return Arrays.copyOf(array, length - numOfDuplicates);
	}
}