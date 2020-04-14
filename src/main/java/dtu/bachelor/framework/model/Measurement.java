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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Sensor_id")
    @JsonBackReference(value = "sensor")
    private Sensor sensor;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trip_id")
    @JsonBackReference(value = "trip")
    private Trip trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Measurement_id")
    @JsonBackReference(value = "measurementtype")
    private MeasurementType measurementtype;

    private LocalDateTime time;

    private int value;

    private double latitude;

    private double longitude;

    // public int getSensorId(){ return sensor.getSensorid(); }

    public String getMeasurementtype() { return measurementtype.getType(); }

    public int getTripId(){ return trip.getTripid(); }

    // public int getMeasurementTypeId() { return measurementtype.getId(); }

    // public int getDeviceId() { return sensor.getDeviceId(); }

    // public int getCarId(){ return sensor.getCarId(); }

}
