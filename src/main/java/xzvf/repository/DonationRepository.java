package xzvf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.model.Donation;

@Repository
@Transactional
public class DonationRepository extends AbstractRepository<Donation> {

}
