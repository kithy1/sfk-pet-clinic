package kandk.springframework.sfkpetclinic.services.map;

import kandk.springframework.sfkpetclinic.model.Owner;
import kandk.springframework.sfkpetclinic.model.Pet;
import kandk.springframework.sfkpetclinic.services.OwnerService;
import kandk.springframework.sfkpetclinic.services.PetService;
import kandk.springframework.sfkpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner saveAndFlush(Owner var1) {
        return null;
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null){
            if(owner.getPets() != null){
                owner.getPets().forEach(pet ->{
                    if(pet.getPetType() != null){
                        if (pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required !");
                    }
                    if (pet.getId() == null){
                        //pet.setOwner(owner);
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                } );
            }
            return super.save(owner);
        } else {
            return null;
        }

    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {

        return this.findAll().stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());
    }


}
