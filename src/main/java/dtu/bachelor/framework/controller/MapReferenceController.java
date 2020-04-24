package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.repository.MapReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapreferences/api")
public class MapReferenceController {

    @Autowired
    private MapReferenceRepository mapReferenceRepository;

    @GetMapping("/mapreference/{id}")
    private MapReference getMapReference(@PathVariable int id){
        return mapReferenceRepository.findByMeasurement_MeasurementId(id);
    }
}
