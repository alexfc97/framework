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
        return sensorRepository.findSensorsById(id);
    }

    @GetMapping("/sensorsByType/{type}")
    private List<Sensor> getSensorsByType(@PathVariable String type) {
        return sensorRepository.findSensorsBySensorType(type);
    }

    @PostMapping("/inputsensor")
    private void addSensor(@RequestBody Sensor sensor) {
        deviceRepository.findById(sensor.getDeviceId())
                .map(device -> sensorRepository.save(sensor))
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + sensor.getDevice().getDeviceid()));
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
