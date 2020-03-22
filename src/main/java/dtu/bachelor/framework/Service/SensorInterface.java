package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Model.Sensor;

import java.util.List;

public interface SensorInterface {
    void addNewSensor(Sensor sensor);

    List<Sensor> getAllSensors();

    void deleteSensor(String sensor);

    void deleteAllSensors(String password);

    List<Sensor> getSensorBytype(String type);
}
