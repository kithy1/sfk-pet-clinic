package kandk.springframework.sfkpetclinic.controllers;

import kandk.springframework.sfkpetclinic.model.Owner;
import kandk.springframework.sfkpetclinic.services.OwnerService;
import kandk.springframework.sfkpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;
    private final VisitService visitService;

    public OwnerController(OwnerService ownerService, VisitService visitService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable() Long ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        Owner owner = ownerService.findById(ownerId);
        modelAndView.addObject(owner);
        return modelAndView;

    }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName()== null) {
            owner.setLastName(""); // empty string signifies broadest possible search

        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownerList";
        }
    }
    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner,BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
   public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult, @PathVariable Long ownerId){
        if(bindingResult.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
