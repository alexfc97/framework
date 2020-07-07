package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.DeviceController;
import dtu.bachelor.framework.controller.SourceTypeController;
import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.SourceType;
import dtu.bachelor.framework.model.Trip;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.SourceTypeRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.transform.Source;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DeviceControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private SourceTypeController sourceTypeController;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @Test
    public void contextLoads() {
        assertThat(deviceController).isNotNull();
    }

    @Test
    public void shouldreturnalldevices() throws Exception {
        // given
        Device device1 = new Device();
        Device device2 = new Device();
        Device device3 = new Device();
        deviceRepository.save(device1);
        deviceRepository.save(device2);
        deviceRepository.save(device3);

        // when
        List<Device> result = deviceController.getAllDevices();

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(device1));
        assertThat(result.contains(device2));
        assertThat(result.contains(device3));
    }

    @Test
    public void returnifDeviceExists(){
        // given
        Device device = new Device();
        SourceType sourceType = new SourceType();
        device.setSourceType(sourceType);
        sourceTypeRepository.save(sourceType);
        LocalDateTime now = LocalDateTime.now();
        device.setTime(now);
        deviceRepository.save(device);

        // when
        Boolean exists = deviceController.deviceExists(device.getDeviceId());

        // then
        assertThat(exists.booleanValue()).isTrue();
    }

    @Test
    public void submitifsourcetypeexists(){
        // given
        Device device = new Device();
        SourceType sourceType = new SourceType();
        sourceTypeRepository.save(sourceType);
        device.setSourceType(sourceType);
        deviceController.addDevice(device);

        // when
        List<Device> devices = deviceController.getAllDevices();

        // then
        assertThat(devices.contains(device));
    }

    @Test
    public void assertiontrue_throwdoesnotexcisterror(){
        Device device = new Device();
        LocalDateTime time = LocalDateTime.now();
        device.setTime(time);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            SourceType sourceType = new SourceType();
            device.setSourceType(sourceType);
            deviceController.addDevice(device);
        });

        String expectedMessage = "sourceType not found with id: " + device.getSourceType().getSourceTypeId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertThat(device.getTime() == time);
    }

    @Test
    public void assertiontrue_throwdevicealreadyexists(){
        Device device = new Device();
        SourceType sourceType = new SourceType();
        device.setSourceType(sourceType);
        sourceTypeRepository.save(sourceType);
        SourceType sourceType1 = new SourceType();
        sourceTypeRepository.save(sourceType1);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            deviceController.addDevice(device);
            Device device1 = new Device();
            device1.setDeviceId(device.getDeviceId());
            device1.setSourceType(sourceType1);
            deviceController.addDevice(device1);
        });

        String expectedMessage = "Device already exists with id: " + device.getDeviceId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void devicesettime(){
        // given
        Device device = new Device();
        SourceType sourceType = new SourceType();
        device.setSourceType(sourceType);
        sourceTypeRepository.save(sourceType);
        deviceController.addDevice(device);

        Device device1 = new Device();
        device1.setTime(LocalDateTime.now());
        SourceType sourceType1 = new SourceType();
        device1.setSourceType(sourceType1);
        sourceTypeRepository.save(sourceType1);
        deviceController.addDevice(device1);

        // when
        Boolean exists = deviceController.deviceExists(device.getDeviceId());

        // then
        assertThat(exists.booleanValue()).isTrue();
    }

}
