package kandk.springframework.sfkpetclinic.bootstrap;

import kandk.springframework.sfkpetclinic.model.Owner;
import kandk.springframework.sfkpetclinic.model.Pet;
import kandk.springframework.sfkpetclinic.model.PetType;
import kandk.springframework.sfkpetclinic.model.Vet;
import kandk.springframework.sfkpetclinic.services.OwnerService;
import kandk.springframework.sfkpetclinic.services.PetTypeService;
import kandk.springframework.sfkpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog =new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat =new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        PetType badger =new PetType();
        badger.setName("Badger");
        PetType saveBadgerPetType = petTypeService.save(badger);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Bicerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456768999");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setName("Fafik");
        mikesPet.setBirthDate(LocalDate.of(2015,12,12));
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 =new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Pilsudski Street");
        owner2.setCity("Miami");
        owner2.setTelephone("123456768999");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(saveCatPetType);
        fionasPet.setName("Mruczek");
        fionasPet.setBirthDate(LocalDate.of(2017,12,12));
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        Owner owner3 =new Owner();
        owner3.setFirstName("Tofik");
        owner3.setLastName("Pumpernikiel");
        owner3.setAddress("123 Mejn Street");
        owner3.setCity("Colorado");
        owner3.setTelephone("666");
        Pet tofiksPet = new Pet();
        tofiksPet.setPetType(saveBadgerPetType);
        tofiksPet.setName("Kosiarz");
        tofiksPet.setBirthDate(LocalDate.of(1983,5,24));
        owner3.getPets().add(tofiksPet);
        ownerService.save(owner3);

        System.out.println("Loaded Owners ... ");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Pinkman");
        vetService.save(vet2);

        System.out.println("Loaded Vets ... ");
    }


}
