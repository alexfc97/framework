package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.Road;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends JpaRepository<Road,Integer> {
}
