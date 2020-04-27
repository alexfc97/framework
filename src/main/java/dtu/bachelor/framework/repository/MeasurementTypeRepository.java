package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Measurement;
import dtu.bachelor.framework.model.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Integer> {
    boolean existsByType(String type);
}
