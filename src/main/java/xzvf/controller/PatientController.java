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

import xzvf.Utility;
import xzvf.enums.BloodType;
import xzvf.enums.Gender;
import xzvf.model.Patient;
import xzvf.model.User;
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
			ModelMap model, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			model.addAttribute("genders", Gender.values());
			model.addAttribute("bloodTypes", BloodType.values());
			return "patient/dataentry";
		}

		String token = String.valueOf(UUID.randomUUID());
		String message = "Please click on the following link to activate your account: "
				+ Utility.getURLWithContextPath(request) + "/patient/confirm/" + token;
		
		patientService.save(patient);
		userService.save(createUserFromPatient(patient, token));
		mailService.sendMail(patient.getEmail(), "Account activation", message);
		
		model.addAttribute("infoMessage", "Patient " + patient.getDisplayString() + " registered successfully");
		return "redirect:/patient/list";
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
    
	private User createUserFromPatient(Patient patient, String token) {
		User user = new User();
		user.setFirstName(patient.getFirstName());
		user.setLastName(patient.getLastName());
		user.setMiddleName(patient.getMiddleName());
		user.setActive(false);
		user.setContactNo(patient.getContactNo());
		user.setEmail(patient.getEmail());
		user.setEntryBy(Utility.getSecurityPrincipal());
		user.setEntryDate(new Date());
		user.setGender(patient.getGender());
		user.setUserName(patient.getEmail());
		user.setPassword("123456");
		user.setConfirmationToken(token);
		return user;
	}
 
    @InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}
