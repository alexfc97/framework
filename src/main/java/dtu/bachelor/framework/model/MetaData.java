package dtu.bachelor.framework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MetaData {

    @Id
    private String name;
    private String type;

}

