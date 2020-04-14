package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class SensorType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String type;

    @OneToMany(mappedBy = "sensorType")
    private List<Sensor> sensor;




    public List<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(List<Sensor> sensor) {
        this.sensor = sensor;
    }
}
