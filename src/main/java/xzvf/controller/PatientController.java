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
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		model.addAttribute("edit", false);
		model.addAttribute("actionParam", "/patient/new?");
		addDefaultAttributes(model);
		return "patient/dataentry";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String save(@Valid Patient patient, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		try {
			validatePatient(patient, result, model, request);
		} catch (IllegalArgumentException e) {
			model.addAttribute("patient", patient);
			model.addAttribute("edit", false);
			model.addAttribute("actionParam", "/patient/new?");
			addDefaultAttributes(model);
			return "patient/dataentry";
		}
		
		String token = String.valueOf(UUID.randomUUID());
		String message = "Please click on the following link to activate your account: "
				+ Utility.getURLWithContextPath(request) + "/patient/confirm/" + token;
		
		String entryBy = Utility.getUser() != null ?
				Utility.getUser() : patient.getUser().getUserName();
				
		patient.setEntryDate(new Date());
		patient.setEntryBy(entryBy);
		
		patient.getUser().setConfirmationToken(token);
		patient.getUser().setEntryBy(entryBy);
		patient.getUser().setEntryDate(new Date());
		
		patientService.save(patient);
		mailService.sendMail(patient.getUser().getEmail(), "Account activation", message);
		
		redirectAttributes.addFlashAttribute("infoMessage", "Registration successful. Please verify account to login");
		String redirectPath = Utility.isLoggedIn() ? "/patient/list" : "/login";
		return "redirect:" + redirectPath;
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Integer id, ModelMap model) {
        Patient patient = (Patient) patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("disableEdit", true);
        addDefaultAttributes(model);
        return "patient/dataentry";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap model) {
        Patient patient = (Patient) patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("edit", true);
        model.addAttribute("actionParam", "/patient/" + id + "?");
        addDefaultAttributes(model);
        return "patient/dataentry";
    }
     
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid Patient patient, BindingResult result,
    		ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable Integer id) {
		try {
			validatePatient(patient, result, model, request);
		} catch (IllegalArgumentException e) {
	        model.addAttribute("patient", patient);
	        model.addAttribute("edit", true);
	        model.addAttribute("actionParam", "/patient/" + id + "?");
	        addDefaultAttributes(model);
			return "patient/dataentry";
		}
 
        patientService.update(patient);
		redirectAttributes.addFlashAttribute("infoMessage", "Update successful");
		String redirectPath = Utility.isLoggedUserAdmin() ? "/patient/list" : "/dashboard";
        return "redirect:" + redirectPath;	
    }
    
    @RequestMapping(value = "/confirm/{token}", method = RequestMethod.GET)
    public String confirm(@PathVariable String token, ModelMap model) {
        userService.activateUser(token);
    	model.addAttribute("title", "Account activation");
        model.addAttribute("message", "Congratulations, your account is now activated!");
        return "app/message";
    }
    
    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public String updateStatus(@PathVariable Integer id, ModelMap model) {
    	Patient patient = (Patient) patientService.findById(id);
    	User user = patient.getUser();
    	user.setActive(!user.getActive());
    	userService.update(user);
    	model.addAttribute("infoMessage", "User status successfully changed");
		return "redirect:/patient/list";
    }
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
    
    private void addDefaultAttributes(ModelMap model) {
    	model.addAttribute("genders", Gender.values());
    	model.addAttribute("bloodTypes", BloodType.values());
    }
    
    private void validatePatient(Patient patient, BindingResult result, ModelMap model, HttpServletRequest request) {
    	if (patient.getId() != null) {
    		Patient oldRecord = (Patient) patientService.findById(patient.getId());
			patient.setEntryBy(oldRecord.getEntryBy());
			patient.setEntryDate(oldRecord.getEntryDate());

			User user = oldRecord.getUser();
			patient.getUser().setEntryBy(user.getEntryBy());
			patient.getUser().setEntryDate(user.getEntryDate());
			patient.getUser().setActive(user.getActive());
		}
    	
		validateModel(result, model);
		validatePassword(patient, model, request);
		validateUsername(patient, model);
	}
    
    private void validateUsername(Patient patient, ModelMap model) {
		User user = userService.findByUsername(patient.getUser().getUserName());
		if (user != null && user.getId() != patient.getUser().getId()) {
			model.addAttribute("errorMessage", "Username already exists. Please select another one.");
			throw new IllegalArgumentException();
		}
	}

	private void validateModel(BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			throw new IllegalArgumentException();
		}
	}

	private void validatePassword(Patient patient, ModelMap model, HttpServletRequest request) {
		if (!request.getParameter("passwordConfirmation").equals(patient.getUser().getPassword())) {
			model.addAttribute("errorMessage", "Password doesn't match");
			throw new IllegalArgumentException();
		}
	}

}
