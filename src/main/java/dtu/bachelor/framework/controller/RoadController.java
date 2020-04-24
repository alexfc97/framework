package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roads/api")
public class RoadController {

    @Autowired
    private RoadRepository roadRepository;
}
