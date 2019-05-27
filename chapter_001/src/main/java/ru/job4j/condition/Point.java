package ru.job4j.condition;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Класс в стиле ООП. Хранит координаты точки и содержит методы по вычислению
 * расстояния до заданной точки и вывод на экран координаты текущей точки.
 */
public class Point {

	private int x;
	private int y;
	private int z;

	/**
	 * Конструктор, который принимает начальное состояние объекта "точка" на плоскости.
	 *
	 * @param first  координата x
	 * @param second координата y
	 */
	public Point(int first, int second) {
		this.x = first;
		this.y = second;
	}

	/**
	 * Конструктор, который принимает начальное состояние объекта "точка" в пространстве.
	 *
	 * @param x  координата x
	 * @param y координата y
	 * @param z координата z
	 */
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Вычисляем расстояние между текущей и заданной точкой на плоскости.
	 *
	 * @param that объект точки, до которой измеряем расстояние.
	 * @return расстояние между точками.
	 */
	public double distance(Point that) {
		return sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2));
	}

	/**
	 * Вычисляем расстояние между текущей и заданной точкой в пространстве.
	 *
	 * @param that объект точки, до которой измеряем расстояние.
	 * @return расстояние между точками.
	 */
	public double distance3d(Point that) {
		return sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2) + pow(this.z - that.z, 2));
	}

	/**
	 * Вывести на экран координаты текущей точки.
	 */
	public void info() {
		System.out.println(String.format("Point[%s, %s]", this.x, this.y));
	}
}