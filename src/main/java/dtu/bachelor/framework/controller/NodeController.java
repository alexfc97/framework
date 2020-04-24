package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nodes/api")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping("/nodes")
    public List<Node> getNodes() { return nodeRepository.findAll();}
}
