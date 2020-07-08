package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@JsonPropertyOrder({"measurementId","time","value","measurementType","latitude","longitude","sourceType","tripId","deviceId","MapReferenceLatitude","MapReferenceLongitude"})
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int measurementId;

    private LocalDateTime time;

    private int value;

    private double latitude;

    private double longitude;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trip_id")
    @JsonBackReference(value = "trip")
    private Trip trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurementType_id")
    @JsonBackReference(value = "measurementType")
    private MeasurementType measurementType;

    @OneToOne(cascade = CascadeType.MERGE)
    private MapReference mapReference;

    public int getTripId() { return trip.getTripId();}

    public MapReference getMapReference() { return mapReference; }

    public double getMapReferenceLatitude() { return mapReference.getMapmatched_latitude(); }

    public double getMapReferenceLongitude() { return mapReference.getMapmatched_longitude(); }

    public MeasurementType getMeasurementType() { return measurementType; }

    public String getType() { return measurementType.getType(); }
}
