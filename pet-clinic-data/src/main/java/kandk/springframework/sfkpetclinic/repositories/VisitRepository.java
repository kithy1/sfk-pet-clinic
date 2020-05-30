package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
