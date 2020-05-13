package calc.actions;

import calc.Action;
import calc.InteractCalc;

public class Add2 implements Action {

    private InteractCalc interactCalc;

    public Add2(InteractCalc interactCalc) {
        this.interactCalc = interactCalc;
    }

    @Override
    public double doAction(double n1) {
        double n2 = interactCalc.ask("введите 2е число: ");
        return n1 + n2;
    }

    @Override
    public String info() {
        return "сумма 2х чисел";
    }
}
