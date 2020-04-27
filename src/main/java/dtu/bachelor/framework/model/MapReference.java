package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class MapReference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mapReferenceId;

    private boolean latitude;

    private boolean longitude;

    @OneToOne(cascade = CascadeType.MERGE)
    private Measurement measurement;
}
