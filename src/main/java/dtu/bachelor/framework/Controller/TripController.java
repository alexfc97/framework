
package dtu.bachelor.framework.Controller;

import dtu.bachelor.framework.Model.Trip;
import dtu.bachelor.framework.Service.TripService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TripController {

    @Resource
    TripService tripService;

    @GetMapping(value = "/triplist")
    public List<Trip> getTrips() {
        return tripService.getAllTrips();
    }

    @PostMapping(value = "/insertTrip")
    public void insertTrip(@RequestBody Trip trip) {
        tripService.addNewTrip(trip);
    }


    @PutMapping(value = "/updateTrip")
    public void updateTrip(@RequestBody Trip trip) {
        tripService.replaceTrip(trip);
    }

/*

    @GetMapping("/trips/{id}")
    Trip returnTripById(@PathVariable Trip trip) {
        tripService.findById(trip);
    }
*/



    @DeleteMapping(value = "/deleteTripById")
    public void deleteTrip(@RequestBody Trip trip) {
        tripService.deleteTrip(trip);
    }

}
