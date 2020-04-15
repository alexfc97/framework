package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.SensorType;
import dtu.bachelor.framework.repository.SensorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensortypes/api")
public class SensorTypeController {

    @Autowired
    private SensorTypeRepository sensorTypeRepository;

    @GetMapping("/sensortypes")
    private List<SensorType> getAllSensorTypes(){return sensorTypeRepository.findAll();}

    @DeleteMapping("/deletebyid/{id}")
    private void deleteSensorTypesById(@PathVariable int id){sensorTypeRepository.deleteById(id);}
}
