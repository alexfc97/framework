package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({"tripId","startPositionLat","startPositionLon","endPositionLat","endPositionLon","startTime","endTime","measurements"})
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

    @OneToMany(mappedBy = "trip")
    private List<Measurement> measurements;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    @JsonBackReference(value = "device")
    private Device device;
}
