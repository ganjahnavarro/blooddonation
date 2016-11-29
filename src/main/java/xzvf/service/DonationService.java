package xzvf.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
