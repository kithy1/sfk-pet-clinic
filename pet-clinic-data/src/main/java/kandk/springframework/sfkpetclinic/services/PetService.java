package kandk.springframework.sfkpetclinic.services;


import kandk.springframework.sfkpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
