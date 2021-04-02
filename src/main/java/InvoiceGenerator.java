public class InvoiceGenerator
{
    private static final double COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_FARE = 5;
    RideRepository  rideRepository;

    public double calculateFare(double distance, int time)
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

    //METHOD TO ADD RIDE LIST
    public void addRides(String userId, Ride[] rides)
    {
        rideRepository.addRide(userId, rides);
    }
}