package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Sensor implements Serializable {

    @Id
    private int id;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Device_id")
    @JsonBackReference(value = "device")
    private Device device;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Sensor_type")
    @JsonBackReference(value = "sensorType")
    private SensorType sensorType;

    public int getDeviceId(){
        return device.getDeviceid();
    }

    public String getSensorType(){
        return sensorType.getType();
    }

    public int getCarId(){
        return device.getCarid();
    }
}
