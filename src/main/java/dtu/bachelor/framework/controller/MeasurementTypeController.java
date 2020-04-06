package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.repository.MeasurementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurementtypes/api")
public class MeasurementTypeController {

    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;
}
