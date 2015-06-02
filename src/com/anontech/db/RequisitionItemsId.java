package com.anontech.db;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RequisitionItemsId implements Serializable{
	private Equipment equipment;
	private Requisition requisition;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipment_id")
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requisition_id")
	public Requisition getRequisition() {
		return requisition;
	}
	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}
	
}
