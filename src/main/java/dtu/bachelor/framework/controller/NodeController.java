package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nodes/api")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping("/nodes")
    public List<Node> getNodes() { return nodeRepository.findAll();}

    @GetMapping("/node/{id}")
    public Optional<Node> getNodeById(@PathVariable int id) { return nodeRepository.findById(id);}
}
