
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest
{
    InvoiceGenerator invoiceGenerator = null;

    public void setup() throws Exception
    {
        invoiceGenerator = new InvoiceGenerator();
    }

    //Calculate Fare
    @Test
    public void givenDistaanceAndTime_ShouldReturnTotalFare()
    {
        double distance = 2.0;
        double time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinimumFare()
    {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare( distance, time);
        Assertions.assertEquals(5, fare, 0.0);
    }

    //Multiple rides
    @Test
    public void giveMultipleRides_ShouldReturnInvoiceSummary()
    {
        Ride[] rides = {
                        new Ride(2.0 , 5),
                        new Ride(0.1 , 1)

                        };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }

    //Given User Id
    @Test
    public void givenUsersID_GenerateTotalFare_ShouldReturnInvoiceSummary() throws InvoiceGeneratorException {
        Ride[] rides = {
                new Ride(0.50 , 0),
                new Ride(5 , 10)

        };
        invoiceGenerator.addRides("UserID_1", rides);
        InvoiceSummary summary = invoiceGenerator.calculateFare("UserID_1");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 65);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }


   // Premium Rides (Bonus)
   @Test
    public void givenDistanceAndTimeWithNormalRideType_GenerateTotalFare_ShouldReturnTheTotalTravelFare()
   {
        Ride[] rides = new Ride[]
                       {
                        new Ride(100, 30, RideType.NORMAL)
                      };
        invoiceGenerator.addRides("User_No_1", rides);
        InvoiceSummary invoiceSummery = null;
        try
        {
            invoiceSummery = invoiceGenerator.getInvoiceSummery("User_No_1");
        }
        catch (InvoiceGeneratorException e)
        {
            e.printStackTrace();
        }
        InvoiceSummary expectedSummery = new InvoiceSummary(1, 1030);
        Assertions.assertEquals(expectedSummery, invoiceSummery);
    }

    @Test
    public void givenDistanceAndTimeWithPremiumRideType_GenerateTotalFare_ShouldReturnTheTotalTravelFare()
    {
        Ride[] rides = {
                       new Ride(50, 12.5, RideType.PREMIUM)
                        };
        invoiceGenerator.addRides("User_No_2", rides);
        InvoiceSummary invoiceSummery = null;
        try
        {
            invoiceSummery = invoiceGenerator.getInvoiceSummery("User_No_2");
        } catch (InvoiceGeneratorException e)
        {
            e.printStackTrace();
        }
        InvoiceSummary expectedSummery = new InvoiceSummary(1, 775);
        Assertions.assertEquals(expectedSummery, invoiceSummery);
    }

    @Test
    public void givenUserIdAsNull_ShouldThrowCustomException()
    {
        try
        {
            Ride[] rides = {
                            new Ride(50, 12.5, RideType.PREMIUM)
                           };
            invoiceGenerator.addRides(null, rides);
            InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummery(null);
            InvoiceSummary expectedSummery = new InvoiceSummary(1, 775);
            Assertions.assertEquals(expectedSummery, invoiceSummery);
        }
        catch (InvoiceGeneratorException e)
        {
            e.printStackTrace();
        }
    }
}

