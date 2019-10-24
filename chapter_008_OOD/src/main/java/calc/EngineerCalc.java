package calc;

import calc.actions.CoSinus;
import calc.actions.Sinus;

import java.util.ArrayList;
import java.util.List;

public class EngineerCalc extends InteractCalc {

    public EngineerCalc(List<Action> additionalActions) {
        super(additionalActions);
    }

    public static void main(String[] args) {
        List<Action> additionalActions = new ArrayList<>();
        additionalActions.add(new Sinus());
        additionalActions.add(new CoSinus());
        new EngineerCalc(additionalActions).start();
    }
}
