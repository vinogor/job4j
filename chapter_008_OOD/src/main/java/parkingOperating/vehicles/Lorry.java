package parkingOperating.vehicles;

public class Lorry extends Vehicle {
    public Lorry() {
        super("heavy");
    }

    @Override
    public String getType() {
        return type;
    }
}