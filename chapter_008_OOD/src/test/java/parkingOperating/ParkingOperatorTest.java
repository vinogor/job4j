package parkingOperating;

import org.junit.Before;
import org.junit.Test;
import parkingOperating.parkings.CarParking;
import parkingOperating.parkings.LorryParking;
import parkingOperating.parkings.Parking;
import parkingOperating.vehicles.Car;
import parkingOperating.vehicles.Lorry;
import parkingOperating.vehicles.Vehicle;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingOperatorTest {

    private static final Vehicle CAR_1 = new Car();
    private static final Vehicle CAR_2 = new Car();
    private static final Vehicle CAR_3 = new Car();
    private static final Vehicle CAR_4 = new Car();
    private static final Vehicle CAR_5 = new Car();

    private static final Vehicle LORRY_1 = new Lorry();
    private static final Vehicle LORRY_2 = new Lorry();
    private static final Vehicle LORRY_3 = new Lorry();
    private static final Vehicle LORRY_4 = new Lorry();

    private Parking carParking;
    private Parking lorryParking;
    private ParkingOperator operator;

    @Before
    public void setUp() {
        carParking = new CarParking(4);
        lorryParking = new LorryParking(3);
        operator = new ParkingOperator();
    }

    @Test
    public void doJob01() {
        boolean result = operator.doJob(carParking, CAR_1);
        assertThat(result, is(true));
    }

    @Test
    public void doJob02() {
        operator.doJob(carParking, CAR_1);
        operator.doJob(carParking, CAR_2);
        operator.doJob(carParking, CAR_3);
        boolean result = operator.doJob(carParking, CAR_4);
        assertThat(result, is(true));
    }

    @Test
    public void doJob03() {
        operator.doJob(lorryParking, CAR_1);
        operator.doJob(lorryParking, CAR_2);
        operator.doJob(lorryParking, CAR_3);
        operator.doJob(lorryParking, CAR_4);
        boolean result = operator.doJob(lorryParking, CAR_5);
        assertThat(result, is(false));
    }

    @Test
    public void doJob04() {
        boolean result = operator.doJob(lorryParking, LORRY_1);
        assertThat(result, is(true));
    }

    @Test
    public void doJob05() {
        operator.doJob(lorryParking, LORRY_1);
        operator.doJob(lorryParking, LORRY_2);
        boolean result = operator.doJob(lorryParking, LORRY_3);
        assertThat(result, is(true));
    }

    @Test
    public void doJob06() {
        operator.doJob(lorryParking, LORRY_1);
        operator.doJob(lorryParking, LORRY_2);
        operator.doJob(lorryParking, LORRY_3);
        boolean result = operator.doJob(lorryParking, LORRY_4);
        assertThat(result, is(false));
    }

    @Test
    public void doJob07() {
        boolean result = operator.doJob(lorryParking, CAR_1);
        assertThat(result, is(false));
    }

    @Test
    public void doJob08() {
        boolean result = operator.doJob(carParking, LORRY_1);
        assertThat(result, is(true));
    }

    @Test
    public void doJob09() {
        operator.doJob(carParking, CAR_1);
        boolean result = operator.doJob(carParking, LORRY_1);
        assertThat(result, is(true));
    }

    @Test
    public void doJob10() {
        operator.doJob(carParking, CAR_1);
        operator.doJob(carParking, CAR_2);
        boolean result = operator.doJob(carParking, LORRY_1);
        assertThat(result, is(false));
    }

    @Test
    public void doJob11() {
        operator.doJob(carParking, CAR_1);
        operator.doJob(carParking, CAR_2);
        operator.doJob(carParking, CAR_3);
        operator.doJob(carParking, CAR_4);
        boolean result = operator.doJob(carParking, LORRY_1);
        assertThat(result, is(false));
    }
}