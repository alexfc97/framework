package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.MeasurementType;
import dtu.bachelor.framework.repository.MeasurementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurementtypes/api")
public class MeasurementTypeController {

    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;

    @GetMapping("/measurementtypes")
    private List<MeasurementType> getAllMeasurementTypes(){return measurementTypeRepository.findAll();}

/*    @DeleteMapping("/deletebyid/{id}")
    private void deleteMeasurementTypeById(@PathVariable int id){measurementTypeRepository.deleteById(id);}*/
}
