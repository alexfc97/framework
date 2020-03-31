package dtu.bachelor.framework.model;

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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sensor")
    @JsonManagedReference
    private Collection<Measurement> measurement;

}
