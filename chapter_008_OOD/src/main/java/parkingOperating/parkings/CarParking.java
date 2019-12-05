package parkingOperating.parkings;

import parkingOperating.vehicles.Vehicle;

public class CarParking extends Parking {

    public CarParking(int size) {
        super(size, "light");
    }

    @Override
    public void park(Vehicle vehicle) {
        cells.add(vehicle);
    }

    @Override
    public String getType() {
        return type;
    }
}