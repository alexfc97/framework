package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class MeasurementType {

    @Id
    private int id;

    private String type;

    @OneToMany(mappedBy = "measurementType")
    private List<Measurement> measurement;



    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }
}

