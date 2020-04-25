package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Road implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roadid;

    private String RoadName;

    @OneToMany(mappedBy = "road")
    private List<Section> sections;
}
