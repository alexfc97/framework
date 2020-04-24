package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.SourceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices/api")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @GetMapping("/devices")
    private List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping("/inputdevice")
    private void addDevice(@RequestBody Device device) {
        if (!sourceTypeRepository.existsById(device.getSourceType().getSourceTypeId())){
            throw new ResourceNotFoundException("sourceType not found with id: " + device.getSourceType().getSourceTypeId());
        } else{
            if (!deviceRepository.existsById(device.getDeviceId())) {
                deviceRepository.save(device);
            } else throw new ResourceNotFoundException("Device already exists with id:" + device.getDeviceId());
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    private void deleteDevice(@PathVariable int id) {
        deviceRepository.deleteById(id);
    }
}
