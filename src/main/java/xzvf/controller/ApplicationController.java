package xzvf.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xzvf.Utility;
import xzvf.model.Donor;
import xzvf.model.Patient;
import xzvf.model.User;
import xzvf.service.DonorService;
import xzvf.service.PatientService;
import xzvf.service.UserService;


@Controller
@RequestMapping("/")
public class ApplicationController {
	
	@Autowired UserService userService;
	@Autowired DonorService donorService;
	@Autowired PatientService patientService;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("message", "Hello World!");
		return "app/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "app/login";
	}
	
	@RequestMapping(value = "/login/{error}", method = RequestMethod.GET)
	public String loginFailure(@PathVariable String error, ModelMap model) {
		Map<String, String> errorMapping = new ConcurrentHashMap<String, String>();
		errorMapping.put("badCredentials", "User and/or password is incorrect.");
		errorMapping.put("accountDisabled", "Your account is not yet verified or might be disabled by administrator.");
		model.addAttribute("errorMessage", errorMapping.get(error));
		return "app/login";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap model) {
		User user = userService.findByUsername(Utility.getUser());
		if (user != null) {
			model.addAttribute("user", user.getFirstName());
			
			Donor donor = donorService.findByUsername(Utility.getUser()); 
			if (donor != null) {
				model.addAttribute("id", donor.getId());
				model.addAttribute("isDonor", true);
			}
			
			Patient patient = patientService.findByUsername(Utility.getUser());
			if (patient != null) {
				model.addAttribute("id", patient.getId());
				model.addAttribute("isPatient", true);
			}
			
			return "app/dashboard";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			model.addAttribute("infoMessage", "You have been logged out.");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "app/accessdenied";
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reports() {
		return "app/reports";
	}
	
}
