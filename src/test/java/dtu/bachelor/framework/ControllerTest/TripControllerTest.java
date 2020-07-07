package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.TripController;
import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.Measurement;
import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.model.Trip;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.MeasurementRepository;
import dtu.bachelor.framework.repository.TripRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TripControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private TripController tripController;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void contextLoads() {
        assertThat(tripController).isNotNull();
    }

    @Test
    public void returnalltrips() {
        // given
        Trip trip = new Trip();
        Device device = new Device();
        deviceRepository.save(device);
        Measurement measurement = new Measurement();
        measurementRepository.save(measurement);
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement);
        LocalDateTime beginning = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        trip.setDuration(Duration.between(beginning,end));
        trip.setDistance(30);
        trip.setEndTime(end);
        trip.setEndPositionLat(55.848109);
        trip.setEndPositionLon(12.848109);
        trip.setMeasurements(measurementList);
        trip.setDevice(device);

        tripController.createNewTrip(trip);

        // when
        List<Trip> result = tripController.getAllTrips();
        Trip trip1 = tripController.getTripById(trip.getTripId()).orElse(null);

        // then
        assertThat(result.contains(trip));
        assertThat(trip1.getEndTime()).isEqualTo(end);
        assertThat(trip1.getEndPositionLat()).isEqualTo(55.848109);
        assertThat(trip1.getEndPositionLon()).isEqualTo(12.848109);
        assertThat(trip1.getDevice() == device);
        assertThat(trip1.getMeasurements() == measurementList);
        assertThat(trip1.equals(trip));
    }

    @Test
    public void deleteTripById(){
        // given
        Trip trip = new Trip();
        Device device = new Device();
        trip.setDevice(device);
        deviceRepository.save(device);
        tripController.createNewTrip(trip);

        // when
        List<Trip> result = tripController.getAllTrips();

        // then
        assertThat(result.contains(trip));
        tripController.deleteTripById(trip.getTripId());
        assertThat(result.contains(trip)).isFalse();
    }

    @Test
    public void assertiontrue_iffirstexceptionthrown(){
        Device device = new Device();
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            Trip trip = new Trip();
            trip.setDevice(device);
            tripController.createNewTrip(trip);
        });

        String expectedMessage = "Device not found with id: " + device.getDeviceId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void assertiontrue_ifsecondexceptionthrown(){
        Trip first = new Trip();
        Device device1 = new Device();
        first.setDevice(device1);
        deviceRepository.save(device1);
        tripController.createNewTrip(first);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            Trip trip = new Trip();
            trip.setTripId(first.getTripId());
            Device device = new Device();
            trip.setDevice(device);
            deviceRepository.save(device);
            tripController.createNewTrip(trip);
        });

        String expectedMessage = "Trip already exists with id: " + first.getTripId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void tripToString(){
        // given
        Trip trip = new Trip();
        tripRepository.save(trip);

        // when
        Trip result = tripController.getTripById(trip.getTripId()).orElse(null);

        // then
        assert result != null;
/*
        assertThat(result.toString()).isEqualTo(trip.toString());
*/
    }
}
