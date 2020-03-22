package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Model.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripDao {

    List<Trip> findAll();

    void insertTrip(Trip trip);

    void updateTrip(Trip trip);

    void deleteTrip(int id);

    List<Trip> findByType(String type);

    List<Trip> findById(int id);

    List<Trip> findByTimes(LocalDateTime time1,LocalDateTime time2);

    List<Trip> findByValue(int value);

    void deleteAllTrips();
}
