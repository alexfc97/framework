package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}

