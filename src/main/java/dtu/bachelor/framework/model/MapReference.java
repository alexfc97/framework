package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class MapReference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mapReferenceId;

    private double mapmatched_latitude;

    private double mapmatched_longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapReference that = (MapReference) o;
        return mapReferenceId == that.mapReferenceId &&
                Double.compare(that.mapmatched_latitude, mapmatched_latitude) == 0 &&
                Double.compare(that.mapmatched_longitude, mapmatched_longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapReferenceId, mapmatched_latitude, mapmatched_longitude);
    }
}
