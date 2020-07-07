package dtu.bachelor.framework.controller;

import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.SourceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/devices/api")
@CrossOrigin
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @GetMapping("/devices")
    @CrossOrigin
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @GetMapping("/exists/{id}")
    @CrossOrigin
    public boolean deviceExists(@PathVariable int id) { return deviceRepository.existsById(id);}

    @PostMapping("/inputdevice")
    @CrossOrigin
    public void addDevice(@RequestBody Device device) {
        if (!sourceTypeRepository.existsById(device.getSourceType().getSourceTypeId())){
            throw new ResourceNotFoundException("sourceType not found with id: " + device.getSourceType().getSourceTypeId());
        } else{
            if (!deviceRepository.existsById(device.getDeviceId())) {
                if (device.getTime() == null) {
                    LocalDateTime now = LocalDateTime.now();
                    device.setTime(now);
                }
                deviceRepository.save(device);
            } else throw new ResourceNotFoundException("Device already exists with id: " + device.getDeviceId());
        }
    }
}
