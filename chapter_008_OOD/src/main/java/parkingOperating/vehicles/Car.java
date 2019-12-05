package parkingOperating.vehicles;

public class Car extends Vehicle {

    public Car() {
        super("light");
    }

    @Override
    public String getType() {
        return type;
    }
}
