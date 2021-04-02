public class InvoiceGenerator
{
    private static  double COST_PER_TIME = 1;
    private static  double MINIMUM_COST_PER_KILOMETER = 10;
    private static  double MINIMUM_FARE = 5;
    RideRepository  rideRepository;

    public InvoiceGenerator()
    {
        this.rideRepository = new RideRepository();
    }
    public static void main(String[] args)
    {
        System.out.println("Welcome To Cab Invoice Generator");
    }

    public double calculateFare( double distance, double time)
    {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides)
    {
        double totalFare = 0;
        for (Ride ride : rides)
        {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
    private InvoiceSummary calculateFare3(Ride[] rideList)
    {
        double totalTravelFareInRS = 0;
        for (Ride rides : rideList)
        {
            totalTravelFareInRS = totalTravelFareInRS + getTravelFare(rides.rideType, rides.distance, rides.time);
        }
        return new InvoiceSummary(rideList.length, totalTravelFareInRS);
    }
    public double getTravelFare(RideType rideType, double distance, double time)
    {
        setValue(rideType);
        double totalTravelFareInRS = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalTravelFareInRS, MINIMUM_FARE);
    }
    private void setValue(RideType rideType)
    {
        MINIMUM_COST_PER_KILOMETER = rideType.costPerKilometerInRs;
        COST_PER_TIME = rideType.costPerMinuteInRs;
        MINIMUM_FARE = rideType.minimumTravelFareInRs;
    }
    public  double getTravelFare(Ride[] rides)
    {
        double totalFare = 0;
        for (Ride ride : rides)
        {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return Math.max(totalFare, MINIMUM_FARE);
    }

    //METHOD TO GET INVOICE SUMMERY BY PASSING USER ID
    public InvoiceSummary calculateFare(String userId)
    {
        Ride[] rideList = rideRepository.getRideList(userId);
        double totalFare = getTravelFare(rideList);
        return new InvoiceSummary(rideList.length, totalFare);
    }
    public InvoiceSummary getInvoiceSummery(String userId) throws InvoiceGeneratorException
    {
        return calculateFare3(rideRepository.getRideList(userId));
    }

    //METHOD TO ADD RIDE LIST
    public void addRides(String userId, Ride[] rides)
    {
        rideRepository.addRide(userId, rides);
    }
}