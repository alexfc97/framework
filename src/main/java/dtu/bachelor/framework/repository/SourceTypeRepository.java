package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTypeRepository extends JpaRepository<SourceType,Integer> {
}
