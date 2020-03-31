package dtu.bachelor.framework.controller;


import dtu.bachelor.framework.model.Sensor;
import dtu.bachelor.framework.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors/api")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/sensors")
    private List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @GetMapping("/sensorsById/{id}")
    private Optional<Sensor> getSensorsById(@PathVariable int id) {
        return sensorRepository.findById(id);
    }

    @GetMapping("/sensorsByType/{type}")
    private List<Sensor> getSensorsByType(@PathVariable String type) {
        return sensorRepository.findByType(type);
    }

    @PostMapping("/inputsensor")
    private void addSensor(@RequestBody Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable int id) {
        sensorRepository.deleteById(id);
    }

    @DeleteMapping("/deleteByType/{type}")
    private void deleteByType(@PathVariable String type) {
        sensorRepository.deleteByType(type);
    }
}
