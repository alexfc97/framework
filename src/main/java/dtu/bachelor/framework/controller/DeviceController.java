package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices/api")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    private List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping("/inputdevice")
    private void addDevice(@RequestBody Device device) {
        deviceRepository.save(device);
    }

    @DeleteMapping("/deletebyid/{id}")
    private void deleteDevice(@PathVariable int id) {
        deviceRepository.deleteById(id);
    }
}
