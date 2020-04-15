package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType,Integer> {
}
