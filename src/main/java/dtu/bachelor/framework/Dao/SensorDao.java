package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Model.Sensor;

import java.util.List;

public interface SensorDao {
    void addNewSensor(Sensor sensor);

    List<Sensor> getAllSensors();

    void deleteSensor(String sensor);

    void deleteAllSensors(String password);

    List<Sensor> getSensorByType(String type);
}
