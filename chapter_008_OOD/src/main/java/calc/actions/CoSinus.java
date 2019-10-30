package calc.actions;

public class CoSinus implements calc.Action {
    @Override
    public double doAction(double n1) {
        return Math.cos(n1);
    }

    @Override
    public String info() {
        return "косинус";
    }
}
