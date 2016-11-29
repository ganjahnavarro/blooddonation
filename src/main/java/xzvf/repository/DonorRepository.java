package xzvf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.model.Donor;

@Repository
@Transactional
public class DonorRepository extends AbstractRepository<Donor>{

}
