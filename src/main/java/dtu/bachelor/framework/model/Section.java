package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int OSMWayPointId;

    @OneToMany
    private List<Node> nodes;

    @ManyToOne()
    private Road road;
}
