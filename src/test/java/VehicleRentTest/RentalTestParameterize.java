package VehicleRentTest;
import VehicleRent.Rental;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class RentalTestParameterize extends TestCase {
    private String customerName;
    private String vehicleName;
    public String startDate;
    public String endDate;
    private long numberOfDays;
    private String expectedCustomerName;
    private long rentCharge;
    //public RentalTest(){}

    public RentalTestParameterize(String customerName, String vehicleName, String startDate, String endDate, long numberOfDays, String expectedCustomerName, long rentCharge) {
        this.customerName = customerName;
        this.vehicleName = vehicleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
        this.expectedCustomerName = expectedCustomerName;
        this.rentCharge = rentCharge;
    }

    private Rental rental;
    @Before
    public void setUp(){
        rental=new Rental("Sachin","audi",100001,"s3",4,"ma30","02-02-2012","12-01-2022",10,7000);
        // rental=new RentalTest("Raj Sharma","BMW","01-01-2012","05-01-2022",4,));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> multipleTest()
    {
        return Arrays.asList(new Object[][]{
                {"Raj Sharma","BMW","01-01-2022","05-01-2022",4,"Raj Sharma",2800},
                {"Sachin","audi","02-01-2022","12-01-2022",10,"Sachin",7000}


        });
    }

    @Test
    public void getCustomerName() {
        assertEquals(expectedCustomerName,customerName);
    }


    @Test
    public void getNumberOfDays() throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        Date date1=sdf.parse(startDate);
        Date date2=sdf.parse(endDate);
        long timeDifference = (date2.getTime() - date1.getTime());
        long daysDifference = (timeDifference
                / (1000 * 60 * 60 * 24))
                % 365;
        assertEquals(numberOfDays,daysDifference);
    }
    @org.junit.Test
    public void getCharges() {
        assertEquals(rentCharge,rental.getRental(numberOfDays),0);
    }


}