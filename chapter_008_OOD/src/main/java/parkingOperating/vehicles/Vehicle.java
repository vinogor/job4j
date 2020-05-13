package parkingOperating.vehicles;

public abstract class Vehicle implements VehicleActions {

    protected String type;

    public Vehicle(String type) {
        this.type = type;
    }
}
