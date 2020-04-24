package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({"deviceId","carId","time","sourceType","trips"})
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deviceId;

    private LocalDateTime time;

    private int carId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sourceType_id")
    @JsonBackReference(value = "sourceType")
    private SourceType sourceType;

    @OneToMany(mappedBy = "device")
    private List<Trip> trips;

    public SourceType getSourceType(){ return sourceType;}
}
