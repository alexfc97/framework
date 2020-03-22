
package dtu.bachelor.framework.Controller;

import dtu.bachelor.framework.Model.Trip;
import dtu.bachelor.framework.Service.TripService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trip/api")
public class TripController {

    @Resource
    TripService tripService;

    @GetMapping(value = "/triplist")
    public List<Trip> getTrips() {
        return tripService.getAllTrips();
    }

    @PostMapping(value = "/inserttrip")
    public void insertTrip(@RequestBody Trip trip) {
        tripService.addNewTrip(trip);
    }

    @PostMapping(value = "/inserttrips")
    public void insertTrip(@RequestBody Trip[] trips) {
        for (Trip trip : trips) {
            tripService.addNewTrip(trip);
        }
    }

    @PutMapping(value = "/updatetrip")
    public void updateTrip(@RequestBody Trip trip) {
        tripService.replaceTrip(trip);
    }

    @GetMapping("/triplist/id/{id}")
    public List<Trip> returnTripById(@PathVariable(value = "id") int id) {
        return tripService.findById(id);
    }

    @GetMapping("/triplist/type/{type}")
    public List<Trip> returnTripByType(@PathVariable(value = "type") String type) {
        return tripService.findByType(type);
    }

    @GetMapping("/triplist/value/{value}")
    public List<Trip> returnTripByValue(@PathVariable(value = "value") int value) {
        return tripService.findByValue(value);
    }
/*
    @GetMapping("/triplist/time/{time1}/{time2}")
    public List<Trip> returnTripByTimes(
            @PathVariable(value ="time1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time1
            ,
            @PathVariable(value = "time2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time2) {
        return tripService.findByTimes(time1,time2);
    }

 */

    @DeleteMapping("/deletetripbyid/{id}")
    public void deleteTrip(@PathVariable(value = "id") int id) {
        tripService.deleteTrip(id);
    }

    @DeleteMapping("/deletetrips/deleteall/{password}")
    public void deleteTrip(@PathVariable(value = "password") String password) {
        tripService.deleteAllTrips(password);
    }

}
