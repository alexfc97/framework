package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({"sourceTypeId","time","devices"})
public class SourceType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int SourceTypeId;

    private String SourceTypeName;

    private LocalDateTime time;

    @OneToMany(mappedBy = "sourceType",cascade = CascadeType.MERGE)
    private List<Device> devices;
}
