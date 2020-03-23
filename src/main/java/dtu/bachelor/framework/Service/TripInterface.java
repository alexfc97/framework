package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Model.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TripInterface {

    List<Trip> getAllTrips();

    void addNewTrip(Trip trip);

    void replaceTrip(Trip trip);

    List<Trip> findById(int id);

    List<Trip> findByTimes(LocalDateTime time1,LocalDateTime time2);

    List<Trip> findByType(String type);

    List<Trip> findByValue(int Value);

    void deleteTrip(int id);

    void deleteAllTrips(String password);
}
