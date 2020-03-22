package dtu.bachelor.framework.Controller;

import dtu.bachelor.framework.Model.Sensor;
import dtu.bachelor.framework.Service.SensorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sensor/api")
public class SensorController {

    @Resource
    SensorService sensorService;

    @PostMapping(value = "/insertsensor")
    public void insertSensor(@RequestBody Sensor sensor) {
        sensorService.addNewSensor(sensor);
    }

    @GetMapping(value = "/sensorlist")
    public List<Sensor> getSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping(value = "/sensorlist/type/{type}")
    public List<Sensor> getSensorbyType(@PathVariable(value = "type") String type) {
        return sensorService.getSensorBytype(type);
    }

    @DeleteMapping(value = "/deletesensor/{sensor}")
    public void deleteSensors(@PathVariable(value = "sensor") String sensor ) {
        sensorService.deleteSensor(sensor);
    }

    @DeleteMapping(value = "/deleteallsensors/{password}")
    public void deleteAllSensors(@PathVariable(value = "password") String password){
        sensorService.deleteAllSensors(password);
    }
}
