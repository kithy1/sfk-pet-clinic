package kandk.springframework.sfkpetclinic.bootstrap;

import kandk.springframework.sfkpetclinic.model.*;
import kandk.springframework.sfkpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count==0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog =new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat =new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        PetType badger =new PetType();
        badger.setName("Badger");
        PetType saveBadgerPetType = petTypeService.save(badger);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty dentist = new Specialty();
        dentist.setDescription("Dentist");
        Specialty savedDentist = specialtyService.save(dentist);


        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.builder().address("123 Bicerel").city("Miami").telephone("123456768999").build();

        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setName("Fafik");
        mikesPet.setBirthDate(LocalDate.of(2015,12,12));
        owner1.addPet(mikesPet);

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
        owner2.addPet(fionasPet);
        //owner2.getPets().add(fionasPet);

        ownerService.save(owner2);


        Visit catVisit = new Visit();
        catVisit.setPet(fionasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");


      visitService.save(catVisit);

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
        owner3.addPet(tofiksPet);
        ownerService.save(owner3);

        System.out.println("Loaded Owners ... ");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedDentist);
        vet1.getSpecialties().add(savedSurgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Pinkman");
        vet2.getSpecialties().add(savedRadiology);
        vetService.save(vet2);

        System.out.println("Loaded Vets ... ");


    }


}
