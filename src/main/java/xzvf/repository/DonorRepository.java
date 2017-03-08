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
import xzvf.model.Donor;

@Repository
@Transactional
public class DonorRepository extends AbstractRepository<Donor>{

	public Donor findByUsername(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("user", "user");
		criteria.add(Restrictions.eq("user.userName", userName));
		return (Donor) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Donor> findDonors(Date startDate, Date endDate, BloodType bloodType, String orderBy) {
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
