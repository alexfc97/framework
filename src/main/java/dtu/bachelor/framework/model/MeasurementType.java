package dtu.bachelor.framework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({"type","unit"})
public class MeasurementType implements Serializable {

    @Id
    private String type;

    private String unit;

    @OneToMany(mappedBy = "measurementType",cascade = CascadeType.MERGE)
    private List<Measurement> measurement;

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }
}

