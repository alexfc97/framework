package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private int carid;

    @OneToMany(mappedBy = "device")
    @JsonManagedReference
    private Collection<Sensor> sensors;
}
