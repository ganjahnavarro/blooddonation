package xzvf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xzvf.enums.Status;
import xzvf.model.Donation;
import xzvf.repository.AbstractRepository;
import xzvf.repository.DonationRepository;

@Service
@Transactional
public class DonationService extends AbstractService {

	@Autowired DonationRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<Donation> findByStatus(Status status) {
		return repository.findByStatus(status);
	}
	
	public List<Donation> findPersonalDonationsByUsername(String userName, Boolean isPatient) {
		return repository.findPersonalDonationsByUsername(userName, isPatient);
	}
	
	public List<Donation> findAssistableDonations(Boolean isPatient) {
		return repository.findAssistableDonations(isPatient);
	}
	
}
