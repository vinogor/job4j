package calc;

import ru.job4j.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    Используя класс Calculator.
// 1. Сделать класс InteractCalc.
// 2. В классе должен быть пользовательский ввод.
// 3. Повторный выбор операции и переиспользование предыдущего вычисления.
// 4. Проект должен следовать SRP.

public class InteractCalc extends Calculator {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public double add(double first, double second) {
        return super.add(first, second);
    }

    @Override
    public double add(double first, double second, double third) {
        return super.add(first, second, third);
    }

    @Override
    public double subtract(double first, double second) {
        return super.subtract(first, second);
    }

    @Override
    public double div(double first, double second) {
        return super.div(first, second);
    }

    @Override
    public double multiple(double first, double second) {
        return super.multiple(first, second);
    }

    private void startDialog() {

        String line = null;
        System.out.print("введите действие: ");
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog(line);
    }

    private void dialog(String line) {
        while (!"стоп".equals(line)) {

            System.out.print("введите число: ");
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("завершаем");
    }

    public static void main(String[] args) {
        new InteractCalc().startDialog();

    }
}
