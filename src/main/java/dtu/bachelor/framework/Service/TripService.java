package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Dao.TripDaoImplementation;
import dtu.bachelor.framework.Model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements TripInterface {

    private final TripDaoImplementation tripDao;

    public TripService(TripDaoImplementation tripDaoImplementation) {
        this.tripDao = tripDaoImplementation;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.findAll();
    }

    @Override
    public void addNewTrip(Trip trip) {
        tripDao.insertTrip(trip);
    }

    @Override
    public void replaceTrip(Trip trip) {
        tripDao.updateTrip(trip);
    }

/*
    @Override
    public Optional<Trip> findById(Trip trip) {
        tripDao.
    }

    @Override
    public Optional<Trip> findByTime(Trip trip) {
        return Optional.empty();
    }

    @Override
    public Optional<Trip> findByType(Trip trip) {
        return Optional.empty();
    }

    @Override
    public Optional<Trip> findByValue(Trip trip) {
        return Optional.empty();
    }*/

    @Override
    public void deleteTrip(Trip trip) {

    }
}
