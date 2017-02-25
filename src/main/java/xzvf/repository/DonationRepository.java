package xzvf.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.enums.Status;
import xzvf.model.Donation;

@Repository
@Transactional
public class DonationRepository extends AbstractRepository<Donation> {

	@SuppressWarnings("unchecked")
	public List<Donation> findByStatus(Status status) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", status));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Donation> findPersonalDonationsByUsername(String userName, Boolean isPatient) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ne("status", Status.PROCESSED));
		
		if (isPatient) {
			criteria.createAlias("patient.user", "patientUser");
			criteria.add(Restrictions.eq("patientUser.userName", userName));
		} else {
			criteria.createAlias("donor.user", "donorUser");
			criteria.add(Restrictions.eq("donorUser.userName", userName));
		}
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Donation> findAssistableDonations(Boolean isPatient) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", Status.APPROVED));
		
		if (isPatient) {
			criteria.add(Restrictions.isNull("patient"));
		} else {
			criteria.add(Restrictions.isNull("donor"));
		}
		
		return criteria.list();
	}
	
}
