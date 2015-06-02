package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name = "user_details")
@PrimaryKeyJoinColumn(name = "users", referencedColumnName="id")
public class UserDetails extends User{
	private String email;
    private String phone;
    private String city;
    private String zip;
    private String designation;
    private String country;
    private String address;
    
    @NotNull
    @Column(name = "email", nullable = false, length = 128)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String argEmail) {
        this.email = argEmail;
    }

    @Column(name = "phone", length = 32)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String argPhone) {
        this.phone = argPhone;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
