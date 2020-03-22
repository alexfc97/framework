package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Dao.TripDaoImplementation;
import dtu.bachelor.framework.Model.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    @Override
    public List<Trip> findById(int id) {
        return tripDao.findById(id);
    }

    @Override
    public List<Trip> findByTimes(LocalDateTime time1, LocalDateTime time2) {
        return tripDao.findByTimes(time1,time2);
    }

    @Override
    public List<Trip> findByType(String type) {
       return tripDao.findByType(type);
    }

    @Override
    public List<Trip> findByValue(int value) {
        return tripDao.findByValue(value);
    }

    @Override
    public void deleteTrip(int id) { tripDao.deleteTrip(id); }

    @Override
    public void deleteAllTrips(String password) {
        if (password.equals("Alexanderfc1997")){
            tripDao.deleteAllTrips();
        } else {
        }
    }
}
