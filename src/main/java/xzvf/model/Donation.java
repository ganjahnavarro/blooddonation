package xzvf.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import xzvf.enums.Status;

@Entity(name = Donation.ENTITY_NAME)
public class Donation implements IRecord {

	public static final String ENTITY_NAME = "tbl_donation";
	private static final long serialVersionUID = -2549929559523986709L;
	
	private Integer id;
	private String title;
	private String description;
	
	private Patient patient;
	private Donor donor;
	
	private Status status = Status.PENDING;
	private Date expiryDate;
	
	private String entryBy;
	private Date entryDate;

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(targetEntity = Donor.class)
	@JoinColumn(name = "donorId")
	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String getEntryBy() {
		return entryBy;
	}

	@Override
	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	@Override
	public Date getEntryDate() {
		return entryDate;
	}

	@Override
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return getTitle();
	}

}
