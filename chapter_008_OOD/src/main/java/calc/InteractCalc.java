package calc;

import calc.actions.*;
import ru.job4j.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class InteractCalc extends Calculator {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private List<Action> actions = new ArrayList<>();
    private List<Action> additionalActions;

    public InteractCalc() {
    }

    public InteractCalc(List<Action> additionalActions) {
        this.additionalActions = additionalActions;
    }

    protected void start() {
        fillActions();
        printMenu();
        mainDialog();
        stop();
    }

    private void fillActions() {
        actions.add(new Add2(this));
        actions.add(new Add3(this));
        actions.add(new Subtract(this));
        actions.add(new Div(this));
        actions.add(new Multiple(this));

        if (additionalActions != null) {
            actions.addAll(additionalActions);
        }
    }

    private void printMenu() {
        print("Доступные операции: \n");
        printMathActions();
        print("    0 - выход из программы \n");
        print("   -1 - показать меню \n");
    }

    private void printMathActions() {
        for (int i = 0; i < actions.size(); i++) {
            Action act = actions.get(i);
            print("    " + (i + 1) + " - " + act.info() + "\n");
        }
    }

    private void mainDialog() {
        double answer;
        double n1;
        double result = 0;
        double reUse = 0;

        answer = ask("введите команду: ");
        // чекать корректность тут и в коне цикла

        while (0 != answer) {
            if (answer == -1) {
                printMenu();
            } else {
                if (reUse == 1) {
                    n1 = result;
                    print("первое число = результат прошлого вычисления = " + n1 + "\n");
                } else {
                    n1 = ask("введите 1е число: ");
                }

                result = actions.get((int) answer - 1).doAction(n1);
                print("результат вычислений: " + result + "\n");

                reUse = ask("сохранить результат для дальнейших вычислений? 1 = да / иное = нет: ");
            }
            answer = ask("введите команду (-1 = показать меню): ");
        }
    }

    public double ask(String msg) {
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
        System.out.print(msg);
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
        new InteractCalc().start();
    }
}