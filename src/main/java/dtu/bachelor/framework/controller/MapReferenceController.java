package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.repository.MapReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapreferences/api")
public class MapReferenceController {

    @Autowired
    private MapReferenceRepository mapReferenceRepository;

    @GetMapping("/mapreferences")
    public List<MapReference> getAllMapReferences() { return mapReferenceRepository.findAll();}

    @GetMapping("/mapreference/{id}")
    public Optional<MapReference> getMapReferenceById(@PathVariable int id){
        return mapReferenceRepository.findById(id);
    }
}
