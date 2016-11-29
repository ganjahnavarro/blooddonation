package xzvf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xzvf.Utility;
import xzvf.model.Donation;
import xzvf.service.DonationService;

@Controller
@RequestMapping("/donation")
public class DonationController {

	@Autowired DonationService donationService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Donation> donations = donationService.findAll();
		model.addAttribute("donations", donations);
		return "donation/list";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Donation donation = new Donation();
		model.addAttribute("donation", donation);
		model.addAttribute("edit", false);
		return "donation/dataentry";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Donation donation, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			return "donation/dataentry";
		}
		
		donationService.save(donation);
		redirectAttributes.addFlashAttribute("infoMessage", "Donation request saved");
		return "redirect:/donation/list";
	}
	
}
