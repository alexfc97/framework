package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Device {

    @Id
    private int deviceid;

    private int carid;

    @OneToMany(mappedBy = "device")
    private List<Sensor> sensors;
}
