package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node,Integer> {

}
