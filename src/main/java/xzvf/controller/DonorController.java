package xzvf.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
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
import xzvf.model.User;
import xzvf.service.DonorService;
import xzvf.service.MailService;
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
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		Donor donor = new Donor();
		model.addAttribute("donor", donor);
		model.addAttribute("edit", false);
		model.addAttribute("actionParam", "/donor/new?");
		addDefaultAttributes(model);
		return "donor/dataentry";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String save(@Valid Donor donor, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		try {
			validateDonor(donor, result, model, request);
		} catch (IllegalArgumentException e) {
			model.addAttribute("donor", donor);
			model.addAttribute("edit", false);
			model.addAttribute("actionParam", "/donor/new?");
			addDefaultAttributes(model);
			return "donor/dataentry";
		}
		
		String token = String.valueOf(UUID.randomUUID());
		String message = "Please click on the following link to activate your account: "
				+ Utility.getURLWithContextPath(request) + "/donor/confirm/" + token;
		
		String entryBy = Utility.getUser() != null ?
				Utility.getUser() : donor.getUser().getUserName();
				
		donor.setEntryDate(new Date());
		donor.setEntryBy(entryBy);
		
		donor.getUser().setConfirmationToken(token);
		donor.getUser().setEntryBy(entryBy);
		donor.getUser().setEntryDate(new Date());
		
		donorService.save(donor);
		mailService.sendMail(donor.getUser().getEmail(), "Account activation", message);
		

		String referrals = request.getParameter("referrals");
		if (referrals != null) {
			for (String referral : referrals.split(",")) {
				referral = referral.trim();
				if (EmailValidator.getInstance().isValid(referral)) {
					String body = "Hi, \n \n"
							+ "Please visit: http://blooddonation-bloodspace.rhcloud.com \n \n"
							+ "This is the place where good donors and real patients meet. "
							+ "The blood you donate gives someone another chance at life. "
							+ "One day that someone may be a close relative, a friend, a loved one - or even you. "
							+ "Donate Blood to give back life.";
					mailService.sendMail(referral, "Blood Donation Campaign", body);
				}
			}
		}
		
		redirectAttributes.addFlashAttribute("infoMessage", "Registration successful. Please verify account to login");
		String redirectPath = Utility.isLoggedIn() ? "/donor/list" : "/login";
		return "redirect:" + redirectPath;
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Integer id, ModelMap model) {
        Donor donor = (Donor) donorService.findById(id);
        model.addAttribute("donor", donor);
        model.addAttribute("disableEdit", true);
        addDefaultAttributes(model);
        return "donor/dataentry";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap model) {
        Donor donor = (Donor) donorService.findById(id);
        model.addAttribute("donor", donor);
        model.addAttribute("edit", true);
        model.addAttribute("actionParam", "/donor/" + id + "?");
        addDefaultAttributes(model);
        return "donor/dataentry";
    }
     
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@Valid Donor donor, BindingResult result,
    		ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable Integer id) {
		try {
			validateDonor(donor, result, model, request);
		} catch (IllegalArgumentException e) {
	        model.addAttribute("donor", donor);
	        model.addAttribute("edit", true);
	        model.addAttribute("actionParam", "/donor/" + id + "?");
	        addDefaultAttributes(model);
			return "donor/dataentry";
		}
 
        donorService.update(donor);
		redirectAttributes.addFlashAttribute("infoMessage", "Update successful");
		String redirectPath = Utility.isLoggedUserAdmin() ? "/donor/list" : "/dashboard";
        return "redirect:" + redirectPath;	
    }
    
    @RequestMapping(value = { "/confirm/{token}" }, method = RequestMethod.GET)
    public String confirm(@PathVariable String token, ModelMap model) {
        userService.activateUser(token);
    	model.addAttribute("title", "Account activation");
        model.addAttribute("message", "Congratulations, your account is now activated!");
        return "app/message";
    }
    
    @RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public String updateStatus(@PathVariable Integer id, ModelMap model) {
    	Donor donor = (Donor) donorService.findById(id);
    	User user = donor.getUser();
    	user.setActive(!user.getActive());
    	userService.update(user);
    	model.addAttribute("infoMessage", "User status successfully changed");
		return "redirect:/donor/list";
    }
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
    
    private void addDefaultAttributes(ModelMap model) {
		model.addAttribute("genders", Gender.values());
		model.addAttribute("bloodTypes", BloodType.values());
	}
    
    private void validateDonor(Donor donor, BindingResult result, ModelMap model, HttpServletRequest request) {
		if (donor.getId() != null) {
			Donor oldRecord = (Donor) donorService.findById(donor.getId());
			donor.setEntryBy(oldRecord.getEntryBy());
			donor.setEntryDate(oldRecord.getEntryDate());

			User user = oldRecord.getUser();
			donor.getUser().setEntryBy(user.getEntryBy());
			donor.getUser().setEntryDate(user.getEntryDate());
			donor.getUser().setActive(user.getActive());
		}
    	
		validateModel(result, model);
		validatePassword(donor, model, request);
		validateUsername(donor, model);
	}
    
    private void validateUsername(Donor donor, ModelMap model) {
		User user = userService.findByUsername(donor.getUser().getUserName());
		if (user != null && user.getId() != donor.getUser().getId()) {
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

	private void validatePassword(Donor donor, ModelMap model, HttpServletRequest request) {
		if (!request.getParameter("passwordConfirmation").equals(donor.getUser().getPassword())) {
			model.addAttribute("errorMessage", "Password doesn't match");
			throw new IllegalArgumentException();
		}
	}

}
