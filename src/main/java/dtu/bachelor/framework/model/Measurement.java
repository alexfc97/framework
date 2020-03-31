package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int measurementid;

    private int tripid;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "Sensor_id")
    @JsonBackReference
    private Sensor sensor;

    private LocalDateTime time;

    private String type;

    private int value;

    private double latitude;

    private double longitude;

}
