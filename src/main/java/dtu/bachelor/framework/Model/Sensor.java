package dtu.bachelor.framework.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sensor {

    @Id
    private String id;
    private String type;

    public Sensor() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sensor(String id, String type) {
        this.id = id;
        this.type = type;
    }
}