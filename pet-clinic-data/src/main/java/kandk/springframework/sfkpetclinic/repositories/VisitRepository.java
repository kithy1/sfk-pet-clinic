package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit,Long> {
}
