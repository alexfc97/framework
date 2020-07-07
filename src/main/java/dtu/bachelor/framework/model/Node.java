package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class Node implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nodeid;

    private float latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeid == node.nodeid &&
                Float.compare(node.latitude, latitude) == 0 &&
                Float.compare(node.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeid, latitude, longitude);
    }

    private float longitude;
}
