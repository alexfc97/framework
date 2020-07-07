package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.NodeController;
import dtu.bachelor.framework.controller.SectionController;
import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.model.Section;
import dtu.bachelor.framework.repository.NodeRepository;
import dtu.bachelor.framework.repository.SectionRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SectionControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private SectionController sectionController;

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    public void contextLoads() {
        assertThat(sectionController).isNotNull();
    }

    @Test
    public void getSections(){
        // given
        Section section = new Section();
        section.setOSMWayPointId(1);
        Section section1 = new Section();
        section1.setOSMWayPointId(2);
        Section section2 = new Section();
        section2.setOSMWayPointId(3);

        sectionRepository.save(section);
        sectionRepository.save(section1);
        sectionRepository.save(section2);

        // when
        List<Section> result = sectionController.getSections();

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(section));
        assertThat(result.contains(section1));
        assertThat(result.contains(section2));
    }
}
