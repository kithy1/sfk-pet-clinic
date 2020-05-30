package kandk.springframework.sfkpetclinic.repositories;

import kandk.springframework.sfkpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
