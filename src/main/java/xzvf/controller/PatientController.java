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
import xzvf.model.Patient;
import xzvf.service.MailService;
import xzvf.service.PatientService;
import xzvf.service.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired UserService userService;
	@Autowired PatientService patientService;
	@Autowired MailService mailService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Patient> patients = patientService.findAll();
		model.addAttribute("patients", patients);
		return "patient/list";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		model.addAttribute("edit", false);
		
		model.addAttribute("genders", Gender.values());
		model.addAttribute("bloodTypes", BloodType.values());
		
		return "patient/dataentry";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Patient patient, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("bloodTypes", BloodType.values());
			return "patient/dataentry";
		}
		
		if (!request.getParameter("passwordConfirmation").equals(patient.getUser().getPassword())) {
//			TODO duplicate code
			Utility.parseErrors(result, model);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("bloodTypes", BloodType.values());
			return "patient/dataentry";
		}
		
		String token = String.valueOf(UUID.randomUUID());
		String message = "Please click on the following link to activate your account: "
				+ Utility.getURLWithContextPath(request) + "/patient/confirm/" + token;
		
		String entryBy = Utility.getSecurityPrincipal() != null ?
				Utility.getSecurityPrincipal() : patient.getUser().getUserName();
				
		patient.setEntryBy(entryBy);
		patient.getUser().setConfirmationToken(token);
		patient.getUser().setEntryBy(entryBy);
		patient.getUser().setEntryDate(new Date());
		
		patientService.save(patient);
		mailService.sendMail(patient.getUser().getEmail(), "Account activation", message);
		
		redirectAttributes.addFlashAttribute("infoMessage", "Registration successful");
		String redirectPath = Utility.isLoggedIn() ? "/patient/list" : "/login"; 
		return "redirect:" + redirectPath;
	}
	
	@RequestMapping(value = { "/edit-{id}-patient" }, method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap model) {
        Patient patient = (Patient) patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("edit", true);
        
        model.addAttribute("genders", Gender.values());
		model.addAttribute("bloodTypes", BloodType.values());
        
        return "patient/dataentry";
    }
     
    @RequestMapping(value = { "/edit-{id}-patient" }, method = RequestMethod.POST)
    public String update(@Valid Patient patient, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
        	Utility.parseErrors(result, model);
        	model.addAttribute("genders", Gender.values());
    		model.addAttribute("bloodTypes", BloodType.values());
            return "patient/dataentry";
        }
 
        patientService.update(patient);
        model.addAttribute("success", "Patient " + patient.getDisplayString()  + " updated successfully");
        return "redirect:/patient/list";	
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
