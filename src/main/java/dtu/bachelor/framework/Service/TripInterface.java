package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Model.Trip;

import java.util.List;

public interface TripInterface {

    List<Trip> getAllTrips();

    void addNewTrip(Trip trip);

    void replaceTrip(Trip trip);
/*
    Optional<Trip> findById(Trip trip);

    Optional<Trip> findByTime(Trip trip);

    Optional<Trip> findByType(Trip trip);

    Optional<Trip> findByValue(Trip trip);*/

    void deleteTrip(Trip trip);

}
