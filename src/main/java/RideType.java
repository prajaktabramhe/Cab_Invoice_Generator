public enum  RideType
{
    //ENUM
    NORMAL(10, 1, 5), PREMIUM(15, 2, 20);

    //FIELDS
    public double costPerKilometerInRs;
    public double costPerMinuteInRs;
    public double minimumTravelFareInRs;

    //CONSTRUCTOR
    RideType(double costPerKilometerInRs, double costPerMinuteInRs, double minimumTravelFareInRs) {
        this.costPerKilometerInRs = costPerKilometerInRs;
        this.costPerMinuteInRs = costPerMinuteInRs;
        this.minimumTravelFareInRs = minimumTravelFareInRs;
    }
}
