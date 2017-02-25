package xzvf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import xzvf.Utility;
import xzvf.model.Donation;
import xzvf.model.Donor;
import xzvf.model.Patient;
import xzvf.service.DonationService;
import xzvf.service.DonorService;
import xzvf.service.PatientService;

@Controller
@RequestMapping("/")
public class ReportController {
	
	@Autowired DonationService donationService;
	@Autowired DonorService donorService;
	@Autowired PatientService patientService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reports/{type}", method = RequestMethod.GET)
	public ModelAndView viewReports(@PathVariable String type) throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("report-" + type + "-list");
		modelAndView.getModelMap().addAttribute("type", type);
		modelAndView.getModelMap().addAttribute("printedBy", Utility.getUser());
		
		switch (type) {
			case "donations":
				List<Donation> donations = donationService.findAll("entryDate");
				modelAndView.getModelMap().addAttribute("donations", donations);
				break;
			case "donors":
				List<Donor> donors = donorService.findAll("entryDate");
				modelAndView.getModelMap().addAttribute("donors", donors);
				break;
			case "patients":
				List<Patient> patients = patientService.findAll("entryDate");
				modelAndView.getModelMap().addAttribute("patients", patients);
				break;
		}
		
		return modelAndView;
	}

}
