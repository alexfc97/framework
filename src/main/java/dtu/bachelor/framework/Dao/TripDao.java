package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Model.Trip;

import java.util.List;

public interface TripDao {

    List<Trip> findAll();

    void insertTrip(Trip trip);

    void updateTrip(Trip trip);

    void deleteTrip(Trip trip);
}
