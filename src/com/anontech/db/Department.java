package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.em.validation.client.constraints.NotEmpty;


@Entity
@Table(name = "department")
public class Department extends Persistent {
	
	private static final long serialVersionUID = 1738734230028866971L;
	
	private Long id;
	private String name;
	private String code;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "department")
    @javax.persistence.SequenceGenerator(name = "department",
    sequenceName = "department_seq")
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 64, nullable = false)
	//@NotNull(message = "Name should not be empty")
	@NotEmpty(message="Name should not be empty")
	@Size(max = 64, message = "Department name should be less than 64 characters")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "code", length = 16, nullable = false)
	//@NotNull(message = "Code should not be empty")
	@NotEmpty(message="Code should not be empty")
	@Size(max = 16, message = "Code should be less than 16 characters")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
