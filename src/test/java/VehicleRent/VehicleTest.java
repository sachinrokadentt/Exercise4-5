package VehicleRent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {
    private Vehicle vehicle;
    @Before
    public void setUp() {
        vehicle=new Vehicle(100001,"BMW","S3",4,"bm30mz","01-01-2022","01-03-2022");//
    }

        @Test
    public void getStartDate() {
        assertEquals("01-01-2022",vehicle.getStartDate());
    }
    @Test
    public void getIdentificationCode() {
        Vehicle vehicle=new Vehicle(100001,"BMW","S3",4,"bm30mz","01-01-2022","01-03-2022");//

        assertEquals(100001,vehicle.getIdentificationCode());
    }

    @Test
    public void getBrand() {
        assertEquals("BMW",vehicle.getBrand());
    }

    @Test
    public void getModel() {
        assertEquals("S3",vehicle.getModel());
    }

    @Test
    public void getNumberOfSeat() {
        assertEquals(4,vehicle.getNumberOfSeat());
    }

    @Test
    public void getLicensePlate() {
        assertEquals("bm30mz",vehicle.getLicensePlate());
    }
}