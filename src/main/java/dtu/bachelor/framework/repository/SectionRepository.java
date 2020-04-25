package dtu.bachelor.framework.repository;

import dtu.bachelor.framework.model.Device;
import dtu.bachelor.framework.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {
}
