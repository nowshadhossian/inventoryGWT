package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vendor")
public class Vendor extends Persistent {
	
	private static final long serialVersionUID = -4786343714596857624L;
	
	private Long id;
	private String name;
	private String address;
	private String phone;
	private String fax;
	private String email;
	
	private String contactName;
	
	private String contactMobileNo;
	
	private Department department;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vendor")
    @javax.persistence.SequenceGenerator(name = "vendor",
    sequenceName = "vendor_seq")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 64, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@Column(name = "address", length = 128, nullable = false)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "phone", length = 32, nullable = false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "fax", length = 32)
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "email", length = 128)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@NotNull
	@Column(name = "contact_name", length = 64, nullable = false)
	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@NotNull
	@Size(min=2)
	@Column(name = "mobile", length = 32, nullable = false)
	public String getContactMobileNo() {
		return contactMobileNo;
	}
	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
