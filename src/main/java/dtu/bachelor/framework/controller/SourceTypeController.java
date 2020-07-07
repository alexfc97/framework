package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.SourceType;
import dtu.bachelor.framework.repository.SourceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sourcetypes/api")
public class SourceTypeController {

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @GetMapping("/sourcetypes")
    @CrossOrigin
    public List<SourceType> getSourceTypes(){
        return sourceTypeRepository.findAll();
    }

    @PostMapping("/inputsourcetype")
    @CrossOrigin
    public void createSourceType(@RequestBody SourceType sourceType){
        if (sourceTypeRepository.existsById(sourceType.getSourceTypeId())){
            throw new ResourceNotFoundException("SourceType already exists with id: " + sourceType.getSourceTypeId());
        } else {
            if (sourceType.getTime() == null) {
                LocalDateTime now = LocalDateTime.now();
                sourceType.setTime(now);
            }
            sourceTypeRepository.save(sourceType);
        }
    }
}
