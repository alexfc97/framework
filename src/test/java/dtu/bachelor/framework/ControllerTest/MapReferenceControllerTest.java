package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.DeviceController;
import dtu.bachelor.framework.controller.MapReferenceController;
import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.repository.DeviceRepository;
import dtu.bachelor.framework.repository.MapReferenceRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MapReferenceControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private MapReferenceController mapReferenceController;

    @Autowired
    private MapReferenceRepository mapReferenceRepository;

    @Test
    public void contextLoads() {
        assertThat(mapReferenceController).isNotNull();
    }

    @Test
    public void shouldreturnallmapreferences() throws Exception {
        // given
        MapReference mapReference = new MapReference();
        MapReference mapReference1 = new MapReference();
        MapReference mapReference2 = new MapReference();
        mapReferenceRepository.save(mapReference);
        mapReferenceRepository.save(mapReference1);
        mapReferenceRepository.save(mapReference2);

        // when
        List<MapReference> result = mapReferenceController.getAllMapReferences();

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(mapReference));
        assertThat(result.contains(mapReference1));
        assertThat(result.contains(mapReference2));
    }

    @Test
    public void returnmapreferencesbyid() throws Exception {
        // given
        MapReference mapReference = new MapReference();
        mapReferenceRepository.save(mapReference);

        // when
        MapReference result = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);

        // then
        assert result != null;
        assertThat(result.getMapReferenceId()).isEqualTo(mapReference.getMapReferenceId());
    }

    @Test
    public void mapReferenceToString(){
        // given
        MapReference mapReference = new MapReference();
        mapReferenceRepository.save(mapReference);

        // when
        MapReference result = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);

        // then
        assert result != null;
        assertThat(result.toString()).isEqualTo(mapReference.toString());
    }

    @Test
    public void MapReferenceHashcode() {
        // given
        MapReference mapReference = new MapReference();
        mapReference.setMapReferenceId(5);
        mapReferenceRepository.save(mapReference);

        // when
        MapReference result = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);

        // then
        if (result != null) {
            assertThat(result.hashCode()).isEqualTo(mapReference.hashCode());
        }

    }
    @Test
    public void mapreferencesequals() {
        // given
        MapReference mapReference = new MapReference();
        mapReference.setMapmatched_longitude(10);
        mapReference.setMapmatched_latitude(20);
        MapReference mapReference1 = new MapReference();
        mapReference1.setMapmatched_longitude(20);
        mapReference1.setMapmatched_latitude(10);

        MapReference mapReference2 = new MapReference();
        mapReference.setMapmatched_longitude(10);
        mapReference.setMapmatched_latitude(10);
        MapReference mapReference3 = new MapReference();
        mapReference1.setMapmatched_longitude(10);
        mapReference1.setMapmatched_latitude(10);

        MapReference something = null;
        Node node = null;

        mapReferenceRepository.save(mapReference);
        mapReferenceRepository.save(mapReference1);
        mapReferenceRepository.save(mapReference2);
        mapReferenceRepository.save(mapReference3);

        // when
        MapReference result = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);
        MapReference result1 = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);
        MapReference result2 = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);
        MapReference result3 = mapReferenceController.getMapReferenceById(mapReference.getMapReferenceId()).orElse(null);



        // then
        if (result != null) {
            assertThat(result.hashCode()).isEqualTo(mapReference.hashCode());
        }
        assertThat(result.equals(result1));
        assertThat(result1.equals(result2));
        assertThat(result2.equals(result3));
        assertThat(result3.equals(result1));
        assertThat(result1.equals(result1));
        assertThat(result.equals(something));
        assertThat(result.equals(node));

    }


}
