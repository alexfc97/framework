package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
public class Sensor implements Serializable {

    @Id
    private int id;
    private String type;
    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private Collection<Measurement> measurements;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Device_id")
    @JsonBackReference
    private Device device;

    public int getDeviceId(){
        return device.getId();
    }

    public int getCarId() {
        return device.getCarid();
    }
}
