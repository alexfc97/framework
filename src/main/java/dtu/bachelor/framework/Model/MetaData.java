package dtu.bachelor.framework.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MetaData {

    @Id
    private String name;
    private String type;

    public MetaData() {
    }

    public MetaData(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

