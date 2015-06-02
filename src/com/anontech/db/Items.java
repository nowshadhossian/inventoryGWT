package com.anontech.db;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Items extends Persistent{
	private Long id;
	private Equipment equipment;
	private String note;
	private Invoice invoice;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "items")
    @javax.persistence.SequenceGenerator(name = "items",
    sequenceName = "items_seq")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipment_id")
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invoice_id")
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
