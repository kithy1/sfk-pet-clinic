package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
