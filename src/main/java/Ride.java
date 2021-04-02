public class Ride {
    //FIELDS
    public double distance;
    public double time;
    RideType rideType;


    public Ride(double distance, double time)
    {
        this.distance = distance;
        this.time = time;
    }
    public Ride(double travelDistanceInKM, double travelTimeInMinutes, RideType rideType)
    {
        this.distance = travelDistanceInKM;
        this.time = travelTimeInMinutes;
        this.rideType = rideType;
    }

}
