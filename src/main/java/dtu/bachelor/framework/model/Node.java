package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Node implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nodeid;

    private float latitude;

    private float longitude;
}
