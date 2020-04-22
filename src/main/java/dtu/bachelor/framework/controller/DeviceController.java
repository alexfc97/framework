package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices/api")
@CrossOrigin
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    @CrossOrigin
    private List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/exists/{id}")
    @CrossOrigin
    private boolean deviceExists(@PathVariable int id) { return deviceRepository.existsById(id);}

    @PostMapping("/inputdevice")
    @CrossOrigin
    private void addDevice(@RequestBody Device device) {
        if (!deviceRepository.existsById(device.getDeviceid())){
            deviceRepository.save(device);
        } else throw new ResourceNotFoundException("Device already exists with id:" + device.getDeviceid());
    }

    @DeleteMapping("/deletebyid/{id}")
    private void deleteDevice(@PathVariable int id) {
        deviceRepository.deleteById(id);
    }
}
