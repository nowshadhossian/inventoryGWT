package com.anontech.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "equipment" , uniqueConstraints = @UniqueConstraint(columnNames = {"make","model"}))
public class Equipment implements Serializable{
	private long id;
	private String make;
	private String model;
	private String note;
	private String description;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "equipment")
    @javax.persistence.SequenceGenerator(name = "equipment",
    sequenceName = "equipment_seq")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
