package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    
    List<Sensor> findByType(String type);

    void deleteByType(String type);
}
