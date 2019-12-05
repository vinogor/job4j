package parkingOperating;

import parkingOperating.parkings.Parking;
import parkingOperating.vehicles.Vehicle;

public class ParkingOperator {

    // контроль соответствия типов
    // и контроль свободное места
    public boolean doJob(Parking parking, Vehicle vehicle) {

        String typeVehicle = vehicle.getType();
        String typeParking = parking.getType();
        int freeCells = parking.getNumOfFreeCells();

        if (freeCells == 0) {
            return false;
        }

        if (typeParking.equals(typeVehicle)) {
            parking.park(vehicle);
            return true;
        }

        if (typeParking.equals("heavy") && typeVehicle.equals("light")) {
            return false;
        }

        if (typeParking.equals("light") && typeVehicle.equals("heavy")) {
            if (freeCells >= 3) {
                parking.park(vehicle);
                parking.park(vehicle);
                parking.park(vehicle);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
