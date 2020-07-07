package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripId;

    private double StartPositionLat;

    private double StartPositionLon;

    private double EndPositionLat;

    private double EndPositionLon;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double distance;

    private Duration duration;

    @OneToMany(mappedBy = "trip")
    private List<Measurement> measurements;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "deviceid")
    @JsonBackReference(value = "device")
    private Device device;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return tripId == trip.tripId &&
                Double.compare(trip.StartPositionLat, StartPositionLat) == 0 &&
                Double.compare(trip.StartPositionLon, StartPositionLon) == 0 &&
                Double.compare(trip.EndPositionLat, EndPositionLat) == 0 &&
                Double.compare(trip.EndPositionLon, EndPositionLon) == 0 &&
                Double.compare(trip.distance, distance) == 0 &&
                Objects.equals(startTime, trip.startTime) &&
                Objects.equals(endTime, trip.endTime) &&
                Objects.equals(duration, trip.duration) &&
                Objects.equals(measurements, trip.measurements) &&
                Objects.equals(device, trip.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, StartPositionLat, StartPositionLon, EndPositionLat, EndPositionLon, startTime, endTime, distance, duration, measurements, device);
    }
}
