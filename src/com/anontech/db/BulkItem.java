package com.anontech.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bulk_item")
@PrimaryKeyJoinColumn(name = "item_id", referencedColumnName="id")
public class BulkItem extends Items{
	private Integer quantity;
	private Integer remainingQuantity;
	private Integer onStock;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public Integer getOnStock() {
		return onStock;
	}
	public void setOnStock(Integer onStock) {
		this.onStock = onStock;
	}
}
