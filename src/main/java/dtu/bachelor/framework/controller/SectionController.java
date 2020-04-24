package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.Road;
import dtu.bachelor.framework.model.Section;
import dtu.bachelor.framework.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections/api")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("sections")
    public List<Section> getRoads() { return sectionRepository.findAll();}
}
