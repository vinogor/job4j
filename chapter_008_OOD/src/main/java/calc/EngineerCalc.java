package calc;

import calc.actions.*;

import java.util.ArrayList;
import java.util.List;

public class EngineerCalc extends InteractCalc {

    public static void main(String[] args) {
        EngineerCalc engineerCalc = new EngineerCalc();

        List<Action> actions = new ArrayList<>();
        actions.add(new Add2(engineerCalc));
        actions.add(new Add3(engineerCalc));
        actions.add(new Subtract(engineerCalc));
        actions.add(new Div(engineerCalc));
        actions.add(new Multiple(engineerCalc));
        actions.add(new Sinus());
        actions.add(new CoSinus());

        engineerCalc.fillActions(actions);
        engineerCalc.start();
    }
}