package ru.job4j.array;

/**
 * Matrix diagonal monotony check.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class MatrixCheck {

	/**
	 * Main calculations.
	 *
	 * @param data input matrix
	 * @return if diagonal is monotone.
	 */
	public boolean mono(boolean[][] data) {
		int length = data.length;
		for (int i = 1; i < length; i++) {
			if ((data[i - 1][i - 1] != data[i][i]) || (data[i - 1][length - i] != data[i][length - i - 1])) {
				return false;
			}
		}
		return true;
	}
}