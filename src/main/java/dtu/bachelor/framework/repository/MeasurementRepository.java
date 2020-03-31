package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT * FROM Measurement WHERE time BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Measurement> findBetweenTimes(
            @Param("time1") LocalDateTime time1,
            @Param("time2") LocalDateTime time2);

    void deleteByTime(LocalDateTime time);

    void deleteByType(String type);

    void deleteByValue(int value);

    void deleteByLatitude(double latitude);

    void deleteByLongitude(double longitude);
}

