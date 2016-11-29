package xzvf.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xzvf.Utility;
import xzvf.enums.BloodType;
import xzvf.enums.Gender;
import xzvf.model.Donor;
import xzvf.service.MailService;
import xzvf.service.DonorService;
import xzvf.service.UserService;

@Controller
@RequestMapping("/donor")
public class DonorController {
	
	@Autowired UserService userService;
	@Autowired DonorService donorService;
	@Autowired MailService mailService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Donor> donors = donorService.findAll();
		model.addAttribute("donors", donors);
		return "donor/list";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Donor donor = new Donor();
		model.addAttribute("donor", donor);
		model.addAttribute("edit", false);
		
		model.addAttribute("genders", Gender.values());
		model.addAttribute("bloodTypes", BloodType.values());
		
		return "donor/dataentry";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Donor donor, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("bloodTypes", BloodType.values());
			return "donor/dataentry";
		}
		
		if (!request.getParameter("passwordConfirmation").equals(donor.getUser().getPassword())) {
//			TODO duplicate code
			Utility.parseErrors(result, model);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("bloodTypes", BloodType.values());
			return "donor/dataentry";
		}
		
		String token = String.valueOf(UUID.randomUUID());
		String message = "Please click on the following link to activate your account: "
				+ Utility.getURLWithContextPath(request) + "/donor/confirm/" + token;
		
		String entryBy = Utility.getSecurityPrincipal() != null ?
				Utility.getSecurityPrincipal() : donor.getUser().getUserName();
				
		donor.setEntryBy(entryBy);
		donor.getUser().setConfirmationToken(token);
		donor.getUser().setEntryBy(entryBy);
		donor.getUser().setEntryDate(new Date());
		
		donorService.save(donor);
		mailService.sendMail(donor.getUser().getEmail(), "Account activation", message);
		
		redirectAttributes.addFlashAttribute("infoMessage", "Registration successful");
		String redirectPath = Utility.isLoggedIn() ? "/donor/list" : "/login"; 
		return "redirect:" + redirectPath;
	}
	
	@RequestMapping(value = { "/edit-{id}-donor" }, method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap model) {
        Donor donor = (Donor) donorService.findById(id);
        model.addAttribute("donor", donor);
        model.addAttribute("edit", true);
        
        model.addAttribute("genders", Gender.values());
		model.addAttribute("bloodTypes", BloodType.values());
        
        return "donor/dataentry";
    }
     
    @RequestMapping(value = { "/edit-{id}-donor" }, method = RequestMethod.POST)
    public String update(@Valid Donor donor, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
        	Utility.parseErrors(result, model);
        	model.addAttribute("genders", Gender.values());
    		model.addAttribute("bloodTypes", BloodType.values());
            return "donor/dataentry";
        }
 
        donorService.update(donor);
        model.addAttribute("success", "Donor " + donor.getDisplayString()  + " updated successfully");
        return "redirect:/donor/list";	
    }
    
    @RequestMapping(value = { "/confirm/{token}" }, method = RequestMethod.GET)
    public String confirm(@PathVariable String token, ModelMap model) {
        userService.activateUser(token);
    	model.addAttribute("title", "Account activation");
        model.addAttribute("message", "Congratulations, your account is now activated!");
        return "app/message";
    }
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}
