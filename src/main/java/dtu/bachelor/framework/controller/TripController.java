package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Trip;
import dtu.bachelor.framework.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips/api")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/trips")
    private List<Trip> getAllTrips(){ return tripRepository.findAll();}

    @PostMapping("/createtrip")
    private void createNewTrip(@RequestBody Trip trip){
        if (!tripRepository.existsById(trip.getTripid())){
            tripRepository.save(trip);
        } else throw new ResourceNotFoundException("Trip already exists with id: " + trip.getTripid());

    }

    @DeleteMapping("/deletetripsbyid/{id}")
    private void deleteTripById(@PathVariable int id){tripRepository.deleteById(id);}
}
