package xzvf.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.model.Patient;

@Repository
@Transactional
public class PatientRepository extends AbstractRepository<Patient> {

	public Patient findByUsername(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("user", "user");
		criteria.add(Restrictions.eq("user.userName", userName));
		return (Patient) criteria.uniqueResult();
	}
	
}
