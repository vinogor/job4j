package calc.actions;

public class Sinus implements calc.Action {
    @Override
    public double doAction(double n1) {
        return Math.sin(n1);
    }

    @Override
    public String info() {
        return "синус";
    }
}
