package dtu.bachelor.framework.controller;


import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Sensor;
import dtu.bachelor.framework.repository.DeviceRepository;
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

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/sensors")
    private List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @GetMapping("/sensorsById/{id}")
    private Sensor getSensorsById(@PathVariable int id) {
        return sensorRepository.findSensorsBySensorid(id);
    }

    @GetMapping("/sensorsByType/{type}")
    private List<Sensor> getSensorsByType(@PathVariable String type) {
        return sensorRepository.findSensorsBySensorType(type);
    }

    @PostMapping("/inputsensor")
    private void addSensor(@RequestBody Sensor sensor) {
        if (!deviceRepository.existsById(sensor.getDevice().getDeviceid())){
            throw new ResourceNotFoundException("Device does not with id:" + sensor.getDevice().getDeviceid());
        } else {
            if (!sensorRepository.existsById(sensor.getSensorid())){
                sensorRepository.save(sensor);
            } else throw new ResourceNotFoundException("Sensor already exists with id:" + sensor.getSensorid());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    private void deleteById(@PathVariable int id) {
        sensorRepository.deleteById(id);
    }

    @DeleteMapping("/deleteByType/{type}")
    private void deleteByType(@PathVariable String type) {
        sensorRepository.deleteSensorsBySensorType(type);
    }
}
