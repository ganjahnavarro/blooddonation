package xzvf.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
