package calc;

import ru.job4j.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Double.NaN;

public class InteractCalc extends Calculator {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private void startDialog() {
        printMenu();
        mainDialog();
    }

    private void printMenu() {
        print("Доступные операции:");
        print("    1 - сумма 2х чисел");
        print("    2 - сумма 3х чисел");
        print("    3 - разность 2х чисел");
        print("    4 - частное 2х чисел");
        print("    5 - произведение 2х чисел");
        print("    0 - выход из программы");
        print("   -1 - показать меню");
    }

    private void mainDialog() {
        double reUseAnswer;
        double n1;
        double n2;
        double result = 0;
        double reUse = 0;

        reUseAnswer = ask("введите команду: ");
        while (0 != reUseAnswer) {
            if (reUse == 1) {
                n1 = result;
                print("первое число = результат прошлого вычисления = " + n1);
            } else {
                n1 = ask("введите 1е число: ");
            }

            n2 = ask("введите 2е число: ");
            result = execute((int) reUseAnswer, n1, n2, result);

            reUse = ask("Сохранить результат для дальнейших вычислений? 1=да / иное=нет: ");
            reUseAnswer = ask("введите команду (-1 = показать меню): ");
        }
        stop();
    }

    private double ask(String msg) {
        print(msg);
        double result = NaN;
        try {
            result = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void print(String msg) {
        System.out.println(msg);
    }

    private double execute(int userChoice, double n1, double n2, double result) {
        double n3;
        switch (userChoice) {
            case -1:
                printMenu();
                break;
            case 1:
                result = add(n1, n2);
                print("сумма: " + result);
                break;
            case 2:
                n3 = ask("введите 3е число: ");
                result = add(n1, n2, n3);
                print("сумма: " + result);
                break;
            case 3:
                result = subtract(n1, n2);
                print("разность: " + result);
                break;
            case 4:
                result = div(n1, n2);
                print("разность: " + result);
                break;
            case 5:
                result = multiple(n1, n2);
                print("произведение: " + result);
                break;
        }
        return result;
    }

    private void stop() {
        print("завершаем работу");
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InteractCalc().startDialog();
    }
}
