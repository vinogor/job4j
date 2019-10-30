package calc.actions;

import calc.Action;
import calc.InteractCalc;

public class Add3 implements Action {

    private InteractCalc interactCalc;

    public Add3(InteractCalc interactCalc) {
        this.interactCalc = interactCalc;
    }

    @Override
    public double doAction(double n1) {
        double n2 = interactCalc.ask("введите 2е число: ");
        double n3 = interactCalc.ask("введите 3е число: ");
        return n1 + n2 + n3;
    }

    @Override
    public String info() {
        return "сумма 3х чисел";
    }
}
