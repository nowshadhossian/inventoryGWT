package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "single_item")
@PrimaryKeyJoinColumn(name = "item_id", referencedColumnName="id")
public class SingleItem extends Items{
	private String serial;
	private ItemStateEnum currentState;
	private Integer currentStateInt;
	private String barcode;

	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	@Transient
	public ItemStateEnum getCurrentState() {
		return currentState;
	}
	public void setCurrentState(ItemStateEnum currentState) {
		this.currentState = currentState;
	}
	@Column(name = "current_state", columnDefinition = "smallint")
	public Integer getCurrentStateInt() {
		if (currentState != null) {
			currentStateInt = currentState.toInt();
        }
        return currentStateInt;
	}
	public void setCurrentStateInt(Integer currentStateInt) {
		this.currentStateInt = currentStateInt;
		currentState = ItemStateEnum.fromInt(currentStateInt);
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
