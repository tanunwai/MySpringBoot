package com.tanunwai.springboot.exam.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name=("User"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 50, nullable = false)
	@Size(min = 2, max = 50, message = "{user.name.size}")
	private String name;
	
	@Column(name = "password", length = 20, nullable = false)
	@NotEmpty(message = "{user.password.empty}")
	private String password;
	
	@Column
	@Temporal(TemporalType.DATE)//有三種格式
	@NotNull(message = "{user.birth.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth;
	
	@Column
	@NotNull(message = "{user.height.null}")
	@Range(min=30, max=300, message = "{user.height.range}")
	private Integer height;
	
	@Column
	@NotNull(message = "{user.weight.null}")
	@Range(min=1, max=300, message="{user.weight.range}")
	private Integer weight;

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getBirth() {
		return birth;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWeight() {
		return weight;
	}
}