package ru.job4j.calculator;

/**
 * Calculator.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 22.05.2019
 */
public class Calculator {

    /**
     * Sum of 2 numbers.
     *
     * @param first  first argument
     * @param second second argument
     * @return addition result
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Sum of 3 numbers.
     *
     * @param first  first argument
     * @param second second argument
     * @param third  third argument
     * @return addition result
     */
    public double add(double first, double second, double third) {
        return add(
                first,
                add(second, third)
        );
    }

    /**
     * Subtract.
     *
     * @param first  first argument
     * @param second second argument
     * @return subtraction result
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Division.
     *
     * @param first  first argument
     * @param second second argument
     * @return division result
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Multiplication.
     *
     * @param first  first argument
     * @param second second argument
     * @return multiplication result
     */
    public double multiple(double first, double second) {
        return first * second;
    }
}
