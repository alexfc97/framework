package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips/api")
public class TripController {

    @Autowired
    private TripRepository tripRepository;
}
