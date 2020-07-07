package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.RoadController;
import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.model.Road;
import dtu.bachelor.framework.model.Section;
import dtu.bachelor.framework.repository.RoadRepository;
import dtu.bachelor.framework.repository.SectionRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoadControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private RoadController roadController;

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    public void contextLoads() {
        assertThat(roadController).isNotNull();
    }

    @Test
    public void getRoads(){
        // given
        Road road = new Road();
        Road road1 = new Road();
        Road road2 = new Road();
        roadRepository.save(road);
        roadRepository.save(road1);
        roadRepository.save(road2);

        // when
        List<Road> result = roadController.getRoads();

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(road));
        assertThat(result.contains(road1));
        assertThat(result.contains(road2));
    }

    @Test
    public void RoadHashcode() {
        // given
        Road road = new Road();
        Section section = new Section();
        sectionRepository.save(section);
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(section);
        road.setSections(sectionList);
        road.setRoadid(1);
        roadRepository.save(road);

        // when
        Road result = roadController.getRoadById(road.getRoadid()).orElse(null);

        // then
        if (result != null) {
            assertThat(result.hashCode() == road.hashCode());
        }

    }
    @Test
    public void roadsequals() {
        // given
        Road road = new Road();
        road.setRoadName("Sænkesøvej");
        Road road1 = new Road();
        road1.setRoadName("Sænkesøvej");

        Road road2 = new Road();
        road.setRoadName("Høsterkøbvej");
        Road road3 = new Road();
        road3.setRoadName("Langelinje");

        Road road4 = null;
        Node node = null;

        roadRepository .save(road);
        roadRepository.save(road1);
        roadRepository.save(road2);
        roadRepository.save(road3);

        // when
        Road result = roadController.getRoadById(road.getRoadid()).orElse(null);
        Road result1 = roadController.getRoadById(road1.getRoadid()).orElse(null);
        Road result2 = roadController.getRoadById(road2.getRoadid()).orElse(null);
        Road result3 = roadController.getRoadById(road3.getRoadid()).orElse(null);



        // then
        if (result != null) {
            assertThat(result.hashCode() == road.hashCode());
        }
        assertThat(result.equals(result1));
        assertThat(result1.equals(result2));
        assertThat(result2.equals(result3));
        assertThat(result3.equals(result1));
        assertThat(result1.equals(result1));
        assertThat(result.equals(road4));
        assertThat(result.equals(node));

    }
}
