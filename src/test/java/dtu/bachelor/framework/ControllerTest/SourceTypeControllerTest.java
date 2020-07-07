package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.DeviceController;
import dtu.bachelor.framework.controller.SourceTypeController;
import dtu.bachelor.framework.exception.ResourceNotFoundException;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.SourceType;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.SourceTypeRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SourceTypeControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private SourceTypeController sourceTypeController;

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @Test
    public void contextLoads() {
        assertThat(sourceTypeController).isNotNull();
    }

    @Test
    public void shouldreturnallsourcetypes() {
        // given
        SourceType sourceType = new SourceType();

        sourceTypeRepository.save(sourceType);

        // when
        List<SourceType> result = sourceTypeController.getSourceTypes();

        // then
        assertThat(result.contains(sourceType));
    }

    @Test
    public void createSourceType(){
        // given
        SourceType sourceType = new SourceType();
        sourceType.setSourceTypeName("some source");
        sourceTypeController.createSourceType(sourceType);
        SourceType sourceType1 = new SourceType();
        sourceType1.setTime(LocalDateTime.now());
        sourceType1.setSourceTypeName("some other source");
        sourceTypeController.createSourceType(sourceType1);

        // when
        SourceType sourceType3 = sourceTypeRepository.findById(sourceType.getSourceTypeId()).orElse(null);

        // then
        assertThat(sourceType3).isNotNull();
    }

    @Test
    public void assertiontrue_ifexceptionthrown(){
        SourceType sourceType1 = new SourceType();
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            SourceType sourceType = new SourceType();
            sourceTypeController.createSourceType(sourceType);
            sourceType1.setSourceTypeId(sourceType.getSourceTypeId());
            sourceTypeController.createSourceType(sourceType1);
        });

        String expectedMessage = "SourceType already exists with id: " + sourceType1.getSourceTypeId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
