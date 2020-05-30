package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty,Long> {
}
