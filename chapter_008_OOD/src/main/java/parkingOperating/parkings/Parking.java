package parkingOperating.parkings;

import parkingOperating.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class Parking implements ParkingActions {

    protected int size;
    protected String type;
    protected List<Vehicle> cells = new ArrayList<>(size);

    public Parking(int size, String type) {
        this.size = size;
        this.type = type;
    }

    public int getNumOfFreeCells() {
        return size - cells.size();
    }
}