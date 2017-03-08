package xzvf.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xzvf.enums.BloodType;
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
	
	public List<Donor> findDonors(Date startDate, Date endDate, BloodType bloodType, String orderBy) {
		return repository.findDonors(startDate, endDate, bloodType, orderBy);
	}

}
