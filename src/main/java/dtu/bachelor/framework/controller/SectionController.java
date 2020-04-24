package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sections/api")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;
}
