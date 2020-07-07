package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Trip;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips/api")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/trips")
    @CrossOrigin
    public List<Trip> getAllTrips(){ return tripRepository.findAll();}

    @GetMapping("/trip/{id}")
    @CrossOrigin
    public Optional<Trip> getTripById(@PathVariable int id) {return tripRepository.findById(id);}

    @PostMapping("/createtrip")
    @CrossOrigin
    public void createNewTrip(@RequestBody Trip trip){
        if (!deviceRepository.existsById(trip.getDevice().getDeviceId())){
            throw new ResourceNotFoundException("Device not found with id: " + trip.getDevice().getDeviceId());
        } else{
            if (!tripRepository.existsById(trip.getTripId())) {
                if (trip.getStartTime() == null) {
                    LocalDateTime now = LocalDateTime.now();
                    trip.setStartTime(now);
                }
                tripRepository.save(trip);
            } else throw new ResourceNotFoundException("Trip already exists with id: " + trip.getTripId());
        }
    }

    @DeleteMapping("/deletetripsbyid/{id}")
    public void deleteTripById(@PathVariable int id){tripRepository.deleteById(id);}
}
