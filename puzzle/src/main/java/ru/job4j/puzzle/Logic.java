package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
	private final int size;
	private final Figure[] figures;
	private int index = 0;

	public Logic(int size) {
		this.size = size;
		this.figures = new Figure[size * size];
	}

	public void add(Figure figure) {
		this.figures[this.index++] = figure;
	}

	public boolean move(Cell source, Cell dest) {
		boolean rst = false;
		int index = this.findBy(source);
		if (index != -1) {
			Cell[] steps = this.figures[index].way(source, dest);
			if (this.isFree(steps)) {
				rst = true;
				this.figures[index] = this.figures[index].copy(dest);
			}
		}
		return rst;
	}

	public boolean isFree(Cell... cells) {
		boolean result = cells.length > 0;
		for (Cell cell : cells) {
			if (this.findBy(cell) != -1) {
				result = false;
				break;
			}
		}
		return result;
	}

	public void clean() {
		for (int position = 0; position != this.figures.length; position++) {
			this.figures[position] = null;
		}
		this.index = 0;
	}

	private int findBy(Cell cell) {
		int rst = -1;
		for (int index = 0; index != this.figures.length; index++) {
			if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
				rst = index;
				break;
			}
		}
		return rst;
	}

	public boolean isWin() {

		// реализация метода на случай если
		// 1) размер поля может меняться
		// 2) кол-во белых фишек не ограничено

		int[][] table = this.convert();
		int len = table.length;

		// ====== 1й вариант: ======

/*
		int[] tmpArr = new int[len];   // создали 1мерный массив длиной = длине поля
		Arrays.fill(tmpArr, 1);   // заполнили массив еденицами

		// проходимся построчно и проверяем
		if (checkLineByLine(table, len, tmpArr)) {
			return true;
		}

		// транпонируем и проверяем ещё раз
		int[][] transposedTable = new int[len][len];

		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < len; ++j) {
				transposedTable[j][i] = table[i][j];
			}
		}

		if (checkLineByLine(transposedTable, len, tmpArr)) {
			return true;
		}

		return false;
*/

		// ====== 2й вариант: ======
		// короче и быстрее
		for (int i = 0; i < len; i++) {

			for (int j = 0; j < len; j++) {
				if (table[i][j] == 0) break;
				if (j == len - 1) return true;
			}

			for (int j = 0; j < len; j++) {
				if (table[j][i] == 0) break;
				if (j == len - 1) return true;
			}
		}
		return false;
	}

	private boolean checkLineByLine(int[][] table, int len, int[] tmpArr) {
		for (int i = 0; i < len; i++) {
			if (Arrays.equals(table[i], tmpArr)) {
				return true;
			}
		}
		return false;
	}

	public int[][] convert() {
		int[][] table = new int[this.size][this.size];
		for (int row = 0; row != table.length; row++) {
			for (int cell = 0; cell != table.length; cell++) {
				int position = this.findBy(new Cell(row, cell));
				if (position != -1 && this.figures[position].movable()) {
					table[row][cell] = 1;
				}
			}
		}
		return table;
	}
}
