package com.wmp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "skill")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Skill implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name = "SKILLID")
	private long skillId;
	
	@Column(name = "ID")
	private long id;

	@Column(name = "SKILL")
	private String skill;

	
	@Column(name = "CREATED", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	public Skill() {
	}

	public Skill(long skillId, long id, String skill, Date createdAt) {
		this.skillId = skillId;
		this.id = id;
		this.skill = skill;
		this.createdAt = new Date();
	}

	public long getSkillId() {
		return this.skillId;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkill() {
		return this.skill;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = new Date();
	}
}
