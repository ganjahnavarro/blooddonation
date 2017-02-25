package xzvf.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xzvf.model.Donor;
import xzvf.repository.AbstractRepository;
import xzvf.repository.DonorRepository;

@Service
@Transactional
public class DonorService extends AbstractService {
	
	@Autowired DonorRepository repository;
	
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public Donor findByUsername(String userName) {
		return repository.findByUsername(userName);
	}

}
