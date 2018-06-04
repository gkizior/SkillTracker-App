package com.wmp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Garrett Kizior on 5/25/2018.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "ID")
	private long id;

	@Column(name = "FN")
	private String firstName;

	@Column(name = "LN")
	private String lastName;

	@Column(name = "DOB")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Column(name = "DOJ")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfJoin;

	@Column(name = "CL")
	private String careerLevel;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIPCODE")
	private String zipcode;

	@Column(name = "CREATED", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "UPDATED", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Employee() {
	}

	public Employee(long id, String firstName, String lastName, Date dateOfBirth, Date dateOfJoin, String careerLevel,
			String address, String city, String state, String zipcode, Date createdAt, Date updatedAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoin = dateOfJoin;
		this.careerLevel = careerLevel;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.createdAt = new Date();
		this.updatedAt = this.createdAt;
	}

	public long getId() {
		return this.id;
	}

	public void setFirstName(String firstName) {
		if (firstName != null && firstName != this.firstName) {
			this.firstName = firstName;
			this.setUpdatedAt();
		}
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		if (lastName != null && lastName != this.lastName) {
			this.lastName = lastName;
			this.setUpdatedAt();
		}
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		if (dateOfBirth != null && dateOfBirth != this.dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			this.setUpdatedAt();
		}
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public Date getDateOfJoin() {
		return this.dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		if (dateOfJoin != null && dateOfJoin != this.dateOfJoin) {
			this.dateOfJoin = dateOfJoin;
			this.setUpdatedAt();
		}
	}

	public void setCareerLevel(String careerLevel) {
		if (careerLevel != null && careerLevel != this.careerLevel) {
			this.careerLevel = careerLevel;
			this.setUpdatedAt();
		}
	}

	public String getCareerLevel() {
		return this.careerLevel;
	}

	public void setAddress(String address) {
		if (address != null && address != this.address) {
			this.address = address;
			this.setUpdatedAt();
		}
	}

	public String getAddress() {
		return this.address;
	}

	public void setCity(String city) {
		if (city != null && city != this.city) {
			this.city = city;
			this.setUpdatedAt();
		}
	}

	public String getCity() {
		return this.city;
	}

	public void setState(String state) {
		if (state != null && state != this.state) {
			this.state = state;
			this.setUpdatedAt();
		}
	}

	public String getState() {
		return this.state;
	}

	public void setZipcode(String zipcode) {
		if (zipcode != null && zipcode != this.zipcode) {
			this.zipcode = zipcode;
			this.setUpdatedAt();
		}
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = new Date();
		this.setUpdatedAt();
	}

	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}
}
