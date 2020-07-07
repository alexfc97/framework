package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.MeasurementTypeController;
import dtu.bachelor.framework.controller.RoadController;
import dtu.bachelor.framework.model.MeasurementType;
import dtu.bachelor.framework.model.Road;
import dtu.bachelor.framework.repository.MeasurementTypeRepository;
import dtu.bachelor.framework.repository.RoadRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MeasurementTypeControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private MeasurementTypeController measurementTypeController;

    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;

    @Test
    public void contextLoads() {
        assertThat(measurementTypeController).isNotNull();
    }

    @Test
    public void getRoads(){
        // given
        MeasurementType measurementType = new MeasurementType();
        measurementType.setType("Acceleration");
        MeasurementType measurementType1 = new MeasurementType();
        measurementType1.setType("Temperature");
        MeasurementType measurementType2 = new MeasurementType();
        measurementType2.setType("Velocity");
        measurementTypeRepository.save(measurementType);
        measurementTypeRepository.save(measurementType1);
        measurementTypeRepository.save(measurementType2);

        // when
        List<MeasurementType> result = measurementTypeController.getAllMeasurementTypes();

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(measurementType));
        assertThat(result.contains(measurementType1));
        assertThat(result.contains(measurementType2));
    }
}
