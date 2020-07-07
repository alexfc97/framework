package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.Road;
import dtu.bachelor.framework.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roads/api")
public class RoadController {

    @Autowired
    private RoadRepository roadRepository;

    @GetMapping("/roads")
    public List<Road> getRoads() {
        return roadRepository.findAll();
    }

    @GetMapping("/road/{id}")
    public Optional<Road> getRoadById(@PathVariable int id) {
        return roadRepository.findById(id);}
}
