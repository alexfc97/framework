package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Section implements Serializable {

    @Id
    private int OSMWayPointId;

    @OneToMany
    private List<Node> nodes;

    @ManyToOne()
    private Road road;

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }
}
