package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Sensor_id")
    @JsonBackReference(value = "sensor")
    private Sensor sensor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Trip_id")
    @JsonBackReference(value = "trip")
    private Trip trip;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Measurement_id")
    @JsonBackReference(value = "measurementtype")
    private MeasurementType measurementType;

    private LocalDateTime time;

    private int value;

    private double latitude;

    private double longitude;


    public int getSensorId(){
        return sensor.getId();
    }

    // These two are the same and therefore measurement Type could be negligible
    public String getSensorType() {
        return sensor.getSensorType();
    }
    public String getMeasurementType() { return measurementType.getType(); }

    public int getTripId(){
        return trip.getId();
    }

    public int getDeviceId() {
        return sensor.getDeviceId();
    }

    public int getCarId(){
        return sensor.getCarId();
    }

}
