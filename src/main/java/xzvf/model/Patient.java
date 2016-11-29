package xzvf.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import xzvf.enums.BloodType;

@Entity(name = Patient.ENTITY_NAME)
public class Patient implements IRecord {

	public static final String ENTITY_NAME = "tbl_patient";
	private static final long serialVersionUID = -5715969777178309372L;
	
	private Integer id;
	private User user;
	private BloodType bloodType = BloodType.A;
	
	private String entryBy;
	private Date entryDate;
	
	public Patient() {
		User user = new User();
		user.setActive(false);
		this.user = user;
	}
	
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
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Enumerated(EnumType.STRING)
	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
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
		return getUser() != null ? getUser().getDisplayString() : null;
	}

}
