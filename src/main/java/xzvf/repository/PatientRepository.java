package xzvf.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.enums.BloodType;
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
	
	@SuppressWarnings("unchecked")
	public List<Patient> findPatients(Date startDate, Date endDate, BloodType bloodType, String orderBy) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("user", "user");
		
		if (bloodType != null) {
			criteria.add(Restrictions.eq("bloodType", bloodType));
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
