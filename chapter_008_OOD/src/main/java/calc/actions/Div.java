package calc.actions;

import calc.InteractCalc;

public class Div implements calc.Action {

    private InteractCalc interactCalc;

    public Div(InteractCalc interactCalc) {
        this.interactCalc = interactCalc;
    }

    @Override
    public double doAction(double n1) {
        double n2 = interactCalc.ask("введите 2е число: ");
        return n1 / n2;
    }

    @Override
    public String info() {
        return "частное 2х чисел";
    }
}
