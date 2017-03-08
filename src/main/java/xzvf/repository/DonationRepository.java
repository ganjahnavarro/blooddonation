package xzvf.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.enums.BloodType;
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
	
	@SuppressWarnings("unchecked")
	public List<Donation> findDonations(Date startDate, Date endDate, BloodType bloodType, Status status, String orderBy) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("patient", "patient", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("donor", "donor", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("patient.user", "patientUser", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("donor.user", "donorUser", JoinType.LEFT_OUTER_JOIN);
		
		if (bloodType != null) {
			Criterion patientCondition = Restrictions.eq("patient.bloodType", bloodType); 
			Criterion donorCondition = Restrictions.and(
					Restrictions.isNull("patient.bloodType"),
					Restrictions.eq("donor.bloodType", bloodType));
			
			criteria.add(Restrictions.or(patientCondition, donorCondition));
		}
		
		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		
		if (startDate != null) {
			criteria.add(Restrictions.ge("entryDate", startDate));
		}
		
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.DATE, 1);
			criteria.add(Restrictions.lt("entryDate", calendar.getTime()));
		}
		
		criteria.addOrder(Order.asc(orderBy));
		return criteria.list();
	}
	
}
