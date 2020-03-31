package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Collection<Measurement> measurement;

}
