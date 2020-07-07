package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.MeasurementController;
import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Measurement;
import dtu.bachelor.framework.model.MeasurementType;
import dtu.bachelor.framework.model.Trip;
import dtu.bachelor.framework.repository.MeasurementRepository;
import dtu.bachelor.framework.repository.TripRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MeasurementControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private MeasurementController measurementController;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private TripRepository tripRepository;

    @Test
    public void getAllMeasurements(){
        // given
        Measurement measurement = new Measurement();
        Measurement measurement1 = new Measurement();
        measurementRepository.save(measurement);
        measurementRepository.save(measurement1);

        // when
        List<Measurement> measurements = measurementController.getAllMeasurements();

        // then
        assertThat(measurements.size() == 2);

        assertThat(measurements.contains(measurement));
        assertThat(measurements.contains(measurement1));
    }

    @Test
    public void checkaddmeasurements(){
        // given
        Measurement measurement = new Measurement();
        MeasurementType measurementType = new MeasurementType();
        measurementType.setType("Acceleration");
        measurement.setMeasurementType(measurementType);
        measurement.setLongitude(55.848470);
        measurement.setLatitude(12.485548);
        Trip trip = new Trip();
        trip.setStartPositionLat(0.0);
        trip.setStartPositionLon(13.123456);
        trip.setStartTime(LocalDateTime.now());
        tripRepository.save(trip);
        measurement.setTrip(trip);
        Measurement measurement1 = new Measurement();
        MeasurementType measurementType1 = new MeasurementType();
        measurementType1.setType("Acceleration");
        measurement1.setMeasurementType(measurementType1);
        measurement1.setLongitude(55.848109);
        measurement1.setLatitude(12.484861);
        Trip trip1 = new Trip();
        trip1.setStartPositionLat(13.123456);
        trip1.setStartPositionLon(0.0);
        tripRepository.save(trip1);
        measurement1.setTrip(trip1);
        List<Measurement> measurementList1 = new ArrayList<>();
        measurementList1.add(measurement);
        measurementList1.add(measurement1);
        measurementRepository.saveAll(measurementList1);

        // when
        List<Measurement> measurementList = measurementController.getAllMeasurements();

        // then
        assertThat(measurementList.get(0).getLongitude() == 12.485415);
        assertThat(measurementList.get(0).getLatitude() == 55.848504);
        assertThat(measurementList.get(1).getLongitude() == 12.485037);
        assertThat(measurementList.get(1).getLatitude() ==  55.848065);

    }

    @Test
    public void asserttrue_tripdoesnotexist(){
        Trip trip = new Trip();
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            // given
            Measurement measurement = new Measurement();
            measurement.setTrip(trip);
            measurementController.addMeasurement(measurement);
        });

        String expectedMessage = "Trip not found with id: " + trip.getTripId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void asserttrue_measurementalreadyexists(){
        Measurement measurement1 = new Measurement();
        Trip trip = new Trip();
        trip.setStartPositionLon(0.0);
        trip.setStartPositionLat(0.0);
        tripRepository.save(trip);
        measurement1.setTrip(trip);
        measurementRepository.save(measurement1);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            // given
            Measurement measurement = new Measurement();
            measurement.setMeasurementId(measurement1.getMeasurementId());
            Trip trip1 = new Trip();
            trip1.setStartPositionLon(1);
            trip1.setStartPositionLat(1);
            tripRepository.save(trip1);
            measurement.setTrip(trip1);
            measurementController.addMeasurement(measurement);
        });

        String expectedMessage = "Measurement already exists with id: " + measurement1.getMeasurementId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
