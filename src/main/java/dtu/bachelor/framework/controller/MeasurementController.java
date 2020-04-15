
package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.Measurement;
import dtu.bachelor.framework.model.MeasurementType;
import dtu.bachelor.framework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurements/api")
public class MeasurementController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;

    @CrossOrigin
    @GetMapping("/measurements")
    private List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @GetMapping("/measurementsbyid/{id}")
    private Optional<Measurement> getMeasurementsById(@PathVariable int id) {
        return measurementRepository.findById(id);
    }

    @GetMapping("/measurementsbytime/{time}")
    private List<Measurement> getMeasurementsByTime(@PathVariable LocalDateTime time) {
        return measurementRepository.findByTime(time);
    }

    @GetMapping("/measurementsbytimes/{time1}/{time2}")
    private List<Measurement> getMeasurementsBetweenTimes(@PathVariable LocalDateTime time1,
                                                     @PathVariable LocalDateTime time2) {
        return measurementRepository.findBetweenTimes(time1,time2);
    }
/*
    @GetMapping("/measurementsbytype/{type}")
    private List<Measurement> getMeasurementsByType(@PathVariable String type) {
        return measurementRepository.findByType(type);
    }

 */

    @GetMapping("/measurementsbyvalue/{value}")
    private List<Measurement> getMeasurementsByValue(@PathVariable int value) {
        return measurementRepository.findByValue(value);
    }

    @GetMapping("/measurementsbylatitude/{latitude}")
    private List<Measurement> getMeasurementsByLatitude(@PathVariable double latitude) {
        return measurementRepository.findByLatitude(latitude);
    }

    @GetMapping("/measurementsbylongitude/{longitude}")
    private List<Measurement> getMeasurementsByLongitude(@PathVariable double longitude) {
        return measurementRepository.findByLongitude(longitude);
    }

    @PostMapping("/inputmeasurement")
    private void addMeasurement(@RequestBody Measurement measurement) {
        if (!sensorRepository.existsById(measurement.getSensor().getSensorid())){
            throw new ResourceNotFoundException("Sensor not found with id:" + measurement.getSensor().getSensorid());
        } else {
            if (!measurementRepository.existsById(measurement.getMeasurementid())){
                if (!tripRepository.existsById(measurement.getTripId())) {
                    tripRepository.save(measurement.getTrip());
                }
                if (!measurementTypeRepository.existsById(measurement.getMeasurementtype().getId())){
                    measurementTypeRepository.save(measurement.getMeasurementtype());
                }
                measurementRepository.save(measurement);
            } else throw new ResourceNotFoundException("Measurement already exists with id:" + measurement.getMeasurementid());
        }
    }

    @DeleteMapping("/deleteall/{password}")
    private void deleteAll(@PathVariable String password) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteAll();
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }

    @DeleteMapping("/deletebyid/{password}/{id}")
    private void deleteById(@PathVariable String password,
                            @PathVariable int id) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteById(id);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }

    @DeleteMapping("/deletebytime/{password}/{time}")
    private void deleteByTime(@PathVariable String password,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME
                              ) LocalDateTime time) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteByTime(time);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }
/*
    @DeleteMapping("/deletebytype/{password}/{type}")
    private void deleteByType(@PathVariable String password,
                              @PathVariable String type) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteByType(type);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }

 */

    @DeleteMapping("/deletebyvalue/{password}/{value}")
    private void deleteByValue(@PathVariable String password,
                           @PathVariable int value) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteByValue(value);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }

    @DeleteMapping("/deletebylatitude/{password}/{latitude}")
    private void deleteByLatitude(@PathVariable String password,
                           @PathVariable double latitude) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteByLatitude(latitude);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }

    @DeleteMapping("/deletebylongitude/{password}/{longitude}")
    private void deleteByLongitude(@PathVariable String password,
                           @PathVariable double longitude) throws IllegalAccessException {
        if (password.equals("Alexanderfc1997")) {
            measurementRepository.deleteByLongitude(longitude);
        } else {
            throw new IllegalAccessException("Password supplied is not the correct password for deleting all records");
        }
    }
}
