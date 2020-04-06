package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String test;

    @OneToMany(mappedBy = "trip")
    private List<Measurement> measurement;





    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }
}
