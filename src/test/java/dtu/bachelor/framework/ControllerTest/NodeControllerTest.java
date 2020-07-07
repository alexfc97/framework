package dtu.bachelor.framework.ControllerTest;

import dtu.bachelor.framework.controller.NodeController;
import dtu.bachelor.framework.model.MapReference;
import dtu.bachelor.framework.model.Node;
import dtu.bachelor.framework.repository.NodeRepository;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NodeControllerTest {

    @AfterClass
    public static void clear(){
        //remove your data
    }

    @Autowired
    private NodeController nodeController;

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void contextLoads() {
        assertThat(nodeController).isNotNull();
    }

    @Test
    public void getnodes(){
        // given
        Node node = new Node();
        node.setLatitude(10);
        node.setLongitude(20);
        node.setNodeid(1);
        Node node1 = new Node();
        node1.setLatitude(10);
        node1.setLongitude(20);
        node1.setNodeid(2);
        Node node2 = new Node();
        node1.setLatitude(20);
        node1.setLongitude(30);
        node1.setNodeid(3);
        Node node3 = new Node();
        node1.setLatitude(10);
        node1.setLongitude(20);
        node1.setNodeid(4);

        nodeRepository.save(node);
        nodeRepository.save(node1);
        nodeRepository.save(node2);
        nodeRepository.save(node3);

        // when
        List<Node> result = nodeController.getNodes();
        Node newnode = nodeController.getNodeById(node3.getNodeid()).orElse(null);
        Node newnode0 = nodeController.getNodeById(node.getNodeid()).orElse(null);

        // then
        assertThat(result.size() == 3);

        assertThat(result.contains(node));
        assertThat(result.contains(node1));
        assertThat(result.contains(node2));
        assertThat(result.get(0).equals(result.get(1)));
        assertThat(newnode.getLatitude()).isEqualTo(node3.getLatitude());
        assertThat(newnode.getLongitude()).isEqualTo(node3.getLongitude());
        assertThat(newnode.equals(newnode0));
    }

    @Test
    public void nodeToString(){
        // given
        Node node = new Node();
        nodeRepository.save(node);

        // when
        Node result = nodeController.getNodeById(node.getNodeid()).orElse(null);

        // then
        assert result != null;
        assertThat(result.toString()).isEqualTo(node.toString());
    }

    @Test
    public void nodeHashcode() {
        // given
        Node node = new Node();
        nodeRepository.save(node);

        // when
        List<Node> result = nodeController.getNodes();

        // then
        assertThat(result.get(0).hashCode()).isEqualTo(node.hashCode());
    }

    @Test
    public void equalobjects(){
        // given
        Node node = new Node();
        node.setLongitude(10);
        node.setLatitude(10);
        Node node1 = new Node();
        node1.setLongitude(10);
        node1.setLatitude(10);

        Node node2 = new Node();
        node.setLongitude(10);
        node.setLatitude(20);
        Node node3 = new Node();
        node1.setLongitude(20);
        node1.setLatitude(10);

        Node something = null;

        nodeRepository.save(node);
        nodeRepository.save(node1);
        nodeRepository.save(node2);
        nodeRepository.save(node3);

        // when
        Node result = nodeController.getNodeById(node.getNodeid()).orElse(null);
        Node result1 = nodeController.getNodeById(node1.getNodeid()).orElse(null);
        Node result2 = nodeController.getNodeById(node2.getNodeid()).orElse(null);
        Node result3 = nodeController.getNodeById(node3.getNodeid()).orElse(null);

        // then
        if (result != null) {
            assertThat(result.hashCode()).isEqualTo(node.hashCode());
        }
        assertThat(result.equals(result1));
        assertThat(result1.equals(result2));
        assertThat(result2.equals(result3));
        assertThat(result3.equals(result1));
        assertThat(result1.equals(result1));
        assertThat(result.equals(something));
    }

}
