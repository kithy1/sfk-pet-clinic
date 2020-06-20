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
    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
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
    public String initNewVisitForm(@PathVariable("petId") Long petId, Model model){
        return "pets/createOrUpdateVisitForm";
    }
    @PostMapping("/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "pets/createOrUpdateForm";
        }else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
