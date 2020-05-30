package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
