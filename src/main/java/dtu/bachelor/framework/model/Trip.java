package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Trip implements Serializable {

    @Id
    private int tripid;

    @OneToMany(mappedBy = "trip")
    private List<Measurement> measurements;

    public void setMeasurement(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
