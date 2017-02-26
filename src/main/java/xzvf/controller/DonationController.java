package xzvf.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xzvf.Utility;
import xzvf.enums.Status;
import xzvf.model.Donation;
import xzvf.model.Donor;
import xzvf.model.Patient;
import xzvf.service.DonationService;
import xzvf.service.DonorService;
import xzvf.service.PatientService;

@Controller
@RequestMapping("/donation")
public class DonationController {

	@Autowired DonationService donationService;
	@Autowired DonorService donorService;
	@Autowired PatientService patientService;
	
	private static final Logger LOGGER = Logger.getLogger(DonationController.class);
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		if (Utility.isLoggedUserAdmin()) {
			model.addAttribute("pendingDonations", donationService.findByStatus(Status.PENDING));
			model.addAttribute("approvedDonations", donationService.findByStatus(Status.APPROVED));
			model.addAttribute("disapprovedDonations", donationService.findByStatus(Status.DISAPPROVED));
			model.addAttribute("cancelledDonations", donationService.findByStatus(Status.CANCELLED));
			model.addAttribute("processedDonations", donationService.findByStatus(Status.PROCESSED));
		} else {
			Boolean isPatient = patientService.findByUsername(Utility.getUser()) != null;
			model.addAttribute("isPatient", isPatient);
			model.addAttribute("personalDonations", donationService.findPersonalDonationsByUsername(Utility.getUser(), isPatient));
			model.addAttribute("assistableDonations", donationService.findAssistableDonations(isPatient));
			model.addAttribute("processedDonations", loadAssistedDonations());
		}
		
		return "donation/list";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Donation donation = new Donation();
		model.addAttribute("donation", donation);
		model.addAttribute("edit", false);
		model.addAttribute("actionParam", "/donation/new?");
		return "donation/dataentry";
	}
	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Donation donation, BindingResult result,
			ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam CommonsMultipartFile fileUpload) throws Exception {
		if (fileUpload != null && fileUpload.getOriginalFilename() != null
    			&& !fileUpload.getOriginalFilename().isEmpty()) {
			donation.setImageFileName(fileUpload.getOriginalFilename());
			donation.setImage(fileUpload.getBytes());
		}
		
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			model.addAttribute("donation", donation);
			model.addAttribute("edit", false);
			model.addAttribute("actionParam", "/donation/new?");
			return "donation/dataentry";
		}
		
		Donor donor = donorService.findByUsername(Utility.getUser());
		if (donor != null) {
			donation.setDonor(donor);
		} else {
			Patient patient = patientService.findByUsername(Utility.getUser());
			donation.setPatient(patient);
		}

		donation.setEntryBy(Utility.getUser());
		donation.setEntryDate(new Date());
		
		donationService.save(donation);
		redirectAttributes.addFlashAttribute("infoMessage", "Donation request saved");
		return "redirect:/donation/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap model) {
        Donation donation = (Donation) donationService.findById(id);
        model.addAttribute("donation", donation);
		model.addAttribute("edit", false);
        model.addAttribute("actionParam", "/donation/" + id + "?");
        return "donation/dataentry";
    }
     
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid Donation donation, BindingResult result,
    		ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes,
    		@PathVariable Integer id, @RequestParam CommonsMultipartFile fileUpload) {
		boolean imageUpdated = false;
    	if (fileUpload != null && fileUpload.getOriginalFilename() != null
    			&& !fileUpload.getOriginalFilename().isEmpty()) {
			donation.setImageFileName(fileUpload.getOriginalFilename());
			donation.setImage(fileUpload.getBytes());
			imageUpdated = true;
		}
		
		if (result.hasErrors()) {
			Utility.parseErrors(result, model);
			model.addAttribute("donation", donation);
			model.addAttribute("edit", true);
			model.addAttribute("actionParam", "/donation/" + id + "?");
			return "donation/dataentry";
		}
		
		Donation previousRecord = (Donation) donationService.findById(id);
		donation.setPatient(donation.getPatient() != null ? donation.getPatient() : previousRecord.getPatient());
		donation.setDonor(donation.getDonor() != null ? donation.getDonor() : previousRecord.getDonor());
		donation.setEntryBy(previousRecord.getEntryBy());
		donation.setEntryDate(previousRecord.getEntryDate());
		
		if (!imageUpdated) {
			donation.setImage(previousRecord.getImage());
			donation.setImageFileName(previousRecord.getImageFileName());
		}
		
        donationService.update(donation);
		redirectAttributes.addFlashAttribute("infoMessage", "Update successful");
        return "redirect:/donation/list";	
    }
	
	@RequestMapping(value = "/{id}/{status}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable Integer id, @PathVariable String status, ModelMap model) {
        Donation donation = (Donation) donationService.findById(id);
        Status enumStatus = Utility.searchEnum(Status.class, status);
        donation.setStatus(enumStatus);
        
        if (enumStatus.equals(Status.PROCESSED)) {
        	if (donation.getPatient() == null) {
        		Patient patient = patientService.findByUsername(Utility.getUser());
        		donation.setPatient(patient);
        	} else {
        		Donor donor = donorService.findByUsername(Utility.getUser());
        		donation.setDonor(donor);
        	}
        }
        
        donationService.update(donation);
		model.addAttribute("infoMessage", "Donation request updated");
		model.addAttribute("donations", donationService.findAll());
		return "redirect:/donation/list";
    }
	
	private List<Donation> loadAssistedDonations() {
		List<Donation> processedDonations = donationService.findByStatus(Status.PROCESSED);
		List<Donation> assistedDonations = new ArrayList<Donation>();
		
		for (Donation donation : processedDonations) {
			if (Utility.getUser().equals(donation.getPatient().getUser().getUserName())
					|| Utility.getUser().equals(donation.getDonor().getUser().getUserName())) {
				assistedDonations.add(donation);
			}
		}
		return assistedDonations;
	}
	
	@RequestMapping(value = "/attachment/{id}/download", method = RequestMethod.GET)
    public void downloadAttachment(@PathVariable Integer id, HttpServletResponse response) {
		Donation donation = (Donation) donationService.findById(id);

		byte[] image = donation.getImage();
		String fileName = donation.getImageFileName();
		
		try {
			File file = new File(System.getProperty("java.io.tmpdir") + fileName);
			FileUtils.writeByteArrayToFile(file, donation.getImage());
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
	        response.setContentLength(image.length);
	        response.getOutputStream().write(image);
		} catch (IOException e) {
			LOGGER.error("Error downloading attachment: " + e.getMessage(), e);
		}
	}
	
	@RequestMapping(value = "/attachment/{id}/remove", method = RequestMethod.GET)
    public void removeAttachment(@PathVariable Integer id, HttpServletResponse response) {
		Donation donation = (Donation) donationService.findById(id);
		donation.setImage(null);
		donation.setImageFileName(null);
		donationService.update(donation);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
}
