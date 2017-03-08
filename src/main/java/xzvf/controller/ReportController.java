package xzvf.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import xzvf.Utility;
import xzvf.enums.BloodType;
import xzvf.enums.Status;
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
	
	private DateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
	
	@RequestMapping(value = "/reports/{type}", method = RequestMethod.POST)
	public ModelAndView printReports(@PathVariable String type, HttpServletRequest request) throws ServletException, IOException {
		Date startDate = tryParseDate(request.getParameter("startDate"));
		Date endDate = tryParseDate(request.getParameter("endDate"));
		
		String paramBloodType = request.getParameter("bloodType");
		BloodType bloodType = paramBloodType != null && !paramBloodType.isEmpty() ? BloodType.valueOf(paramBloodType) : null;
				
		String paramStatus = request.getParameter("status");
		Status status = paramStatus != null && !paramStatus.isEmpty() ? Status.valueOf(paramStatus) : null;
		
		String orderBy = request.getParameter("orderBy");
		
		ModelAndView modelAndView = new ModelAndView("report-" + type + "-list");
		modelAndView.getModelMap().addAttribute("type", type);
		modelAndView.getModelMap().addAttribute("printedBy", Utility.getUser());
		
		switch (type) {
			case "donations":
				List<Donation> donations = donationService.findDonations(startDate, endDate, bloodType, status, orderBy);
				modelAndView.getModelMap().addAttribute("donations", donations);
				break;
			case "donors":
				List<Donor> donors = donorService.findDonors(startDate, endDate, bloodType, orderBy);
				modelAndView.getModelMap().addAttribute("donors", donors);
				break;
			case "patients":
				List<Patient> patients = patientService.findPatients(startDate, endDate, bloodType, orderBy);
				modelAndView.getModelMap().addAttribute("patients", patients);
				break;
		}
		
		return modelAndView;
	}

	private Date tryParseDate(String value) {
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
	
}
