package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Model.Sensor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorDaoImplementation implements SensorDao {

    public SensorDaoImplementation(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public void addNewSensor(Sensor sensor) {

    }

    @Override
    public List<Sensor> getAllSensors() {
        return null;
    }

    @Override
    public void deleteSensor(String sensor) {

    }

    @Override
    public void deleteAllSensors(String password) {

    }

    @Override
    public List<Sensor> getSensorByType(String type) {
        return null;
    }
}
