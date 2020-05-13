package parkingOperating.parkings;

import parkingOperating.vehicles.Vehicle;

public class LorryParking extends Parking {
    public LorryParking(int size) {
        super(size, "heavy");
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
