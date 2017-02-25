package xzvf.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
}
