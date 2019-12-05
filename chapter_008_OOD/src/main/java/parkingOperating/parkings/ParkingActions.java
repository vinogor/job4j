package parkingOperating.parkings;

import parkingOperating.vehicles.Vehicle;

public interface ParkingActions {

    void park(Vehicle vehicle);
    String getType();

}
