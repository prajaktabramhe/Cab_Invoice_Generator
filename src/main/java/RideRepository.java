import java.util.HashMap;
import java.util.Map;

public class RideRepository
{
    Map<String, Ride[]> rideList;

    public RideRepository()
    {
        this.rideList = new HashMap<>();
    }

    public void addRide(String userId, Ride[] rides)
    {
        rideList.put(userId, rides);
    }

    public Ride[] getRideList(String userId)
    {
        return rideList.get(userId);
    }
}
