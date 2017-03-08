package xzvf.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xzvf.enums.BloodType;
import xzvf.model.Patient;
import xzvf.repository.AbstractRepository;
import xzvf.repository.PatientRepository;

@Service
@Transactional
public class PatientService extends AbstractService {

	@Autowired PatientRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public Patient findByUsername(String userName) {
		return repository.findByUsername(userName);
	}
	
	public List<Patient> findPatients(Date startDate, Date endDate, BloodType bloodType, String orderBy) {
		return repository.findPatients(startDate, endDate, bloodType, orderBy);
	}
	
}
