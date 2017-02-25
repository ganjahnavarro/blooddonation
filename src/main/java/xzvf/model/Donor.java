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

@Entity(name = Donor.ENTITY_NAME)
public class Donor implements IRecord {

	public static final String ENTITY_NAME = "tbl_donor";
	private static final long serialVersionUID = -3146028678796861338L;
	
	private Integer id;
	private User user;
	private BloodType bloodType = BloodType.A;
	
	private String entryBy;
	private Date entryDate;
	
	public Donor() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloodType == null) ? 0 : bloodType.hashCode());
		result = prime * result + ((entryBy == null) ? 0 : entryBy.hashCode());
		result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donor) {
			return this.getId().equals(((Donor) obj).getId());
		}
		return super.equals(obj);
	}
	
}
