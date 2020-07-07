
package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Measurement;
import dtu.bachelor.framework.repository.*;
import dtu.bachelor.framework.service.Interpolation;
import dtu.bachelor.framework.service.MapMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/measurements/api")
public class MeasurementController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;

    @Autowired
    private MapReferenceRepository mapReferenceRepository;

    @CrossOrigin
    @GetMapping("/measurements")
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @PostMapping("/inputmeasurement")
    public void addMeasurement(@RequestBody Measurement measurement) {
        if (!tripRepository.existsById(measurement.getTrip().getTripId())){
            throw new ResourceNotFoundException("Trip not found with id: " + measurement.getTrip().getTripId());
        } else {
            if (!measurementRepository.existsById(measurement.getMeasurementId())){
                if (!measurementTypeRepository.existsByType(measurement.getMeasurementType().getType())){
                    measurementTypeRepository.save(measurement.getMeasurementType());
                }
                if (tripRepository.findById(measurement.getTripId()).get().getStartPositionLon() == 0.0){
                    tripRepository.findById(measurement.getTripId()).get().setStartPositionLon(measurement.getLongitude());
                }
                if (tripRepository.findById(measurement.getTripId()).get().getStartPositionLat() == 0.0){
                    tripRepository.findById(measurement.getTripId()).get().setStartPositionLat(measurement.getLatitude());
                }
                if (tripRepository.findById(measurement.getTripId()).get().getStartTime() == null){
                    tripRepository.findById(measurement.getTripId()).get().setStartTime(measurement.getTime());
                }
                measurementRepository.save(measurement);
            } else throw new ResourceNotFoundException("Measurement already exists with id: " + measurement.getMeasurementId());
        }
    }

    @PostMapping("/inputmeasurements")
    @CrossOrigin
    public void addMeasurements(@RequestBody List<Measurement> measurementList) {

        Interpolation.Interpolate(measurementList,"linear");

        MapMatching.match(measurementList);

        for (Measurement measurement : measurementList) {
            mapReferenceRepository.save(measurement.getMapReference());
            addMeasurement(measurement);
        }
    }
}
