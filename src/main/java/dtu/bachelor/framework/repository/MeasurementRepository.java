package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByType(String measurementtype);

    List<Measurement> findByValue(Integer measurementvalue);

    List<Measurement> findByTime(LocalDateTime measurementtime);

    List<Measurement> findByLatitude(double measurementlatitude);

    List<Measurement> findByLongitude(double measurementlongitude);

    List<Measurement> findBetweenTimes(LocalDateTime time1, LocalDateTime time2);

    void deleteByTime(LocalDateTime time);

    void deleteByType(String type);

    void deleteByValue(int value);

    void deleteByLatitude(double latitude);

    void deleteByLongitude(double longitude);
}

