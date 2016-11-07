package xzvf.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xzvf.model.Patient;

@Repository
@Transactional
public class PatientRepository extends AbstractRepository<Patient> {

}
