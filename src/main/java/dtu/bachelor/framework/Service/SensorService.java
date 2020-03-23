package dtu.bachelor.framework.Service;

import dtu.bachelor.framework.Dao.SensorDaoImplementation;
import dtu.bachelor.framework.Model.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService implements SensorInterface {

    private final SensorDaoImplementation sensorDao;

    public SensorService(SensorDaoImplementation sensorDaoImplementation) {
        this.sensorDao = sensorDaoImplementation;
    }

    @Override
    public void addNewSensor(Sensor sensor) {
        sensorDao.addNewSensor(sensor);
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorDao.getAllSensors();
    }

    @Override
    public void deleteSensor(String sensor) {
        sensorDao.deleteSensor(sensor);
    }

    @Override
    public void deleteAllSensors(String password) {
        sensorDao.deleteAllSensors(password);
    }

    @Override
    public List<Sensor> getSensorBytype(String type) {
        return sensorDao.getSensorByType(type);
    }
}
