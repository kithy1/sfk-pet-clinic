package kandk.springframework.sfkpetclinic.controllers;

import kandk.springframework.sfkpetclinic.formmatters.PetValidator;
import kandk.springframework.sfkpetclinic.model.Pet;
import kandk.springframework.sfkpetclinic.model.Visit;
import kandk.springframework.sfkpetclinic.services.PetService;
import kandk.springframework.sfkpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }
//    @InitBinder
//    public void setAllowedFields(WebDataBinder webDataBinder){
//
//        webDataBinder.setDisallowedFields("id");
//    }
    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {

        dataBinder.setValidator(new PetValidator());
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet",pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }
    @GetMapping("/visits/new")
    public String initNewVisitForm(){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/new")
    public String processNewVisitForm(@Valid Visit visit, Pet pet, BindingResult result){

        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
    @GetMapping("/visits/{visitId}/edit")
    public String initUpdateForm(@PathVariable Long visitId, Model model) {
        Visit visit = visitService.findById(visitId);
        model.addAttribute("visit", visit);
        System.out.println("********************>"+visit.getId());
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/{visitId}/edit")
//    @Transactional
    public String processUpdateForm(@PathVariable Long visitId, @Valid Visit visit, BindingResult bindingResult,  Pet pet, Model model) {
        if (bindingResult.hasErrors()) {
            visit.setPet(pet);
            model.addAttribute("visit", visit);
            return "pets/createOrUpdateVisitForm";
        } else {
            System.out.println("--------------------->"+visit.getId());
            visit.setId(visitId);
            System.out.println("--------------------->"+visit.getId());
            pet.addVisit(visit);


            System.out.println("--------------------->"+visit.getId());
            petService.save(pet);
            //visitService.saveAndFlush(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
