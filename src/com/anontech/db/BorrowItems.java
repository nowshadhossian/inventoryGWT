package com.anontech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@IdClass (BorrowItemsId.class)
@Table(name = "borrow_items")
public class BorrowItems{
	private Borrow borrow;
	private Items item;
	private ItemStateEnum itemState;
	private Integer itemStateInt;
	private int quantity;
	private String remarks;
	
	@Id
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}
	
	@Id
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	
	@Transient
	public ItemStateEnum getItemState() {
		return itemState;
	}
	public void setItemState(ItemStateEnum itemState) {
		this.itemState = itemState;
	}
	@Column(name = "item_state", nullable = false, columnDefinition = "smallint")
	public Integer getItemStateInt() {
		if (itemState != null) {
            itemStateInt = itemState.toInt();
        }
        return itemStateInt;
	}
	public void setItemStateInt(Integer itemStateInt) {
		this.itemStateInt = itemStateInt;
		itemState = ItemStateEnum.fromInt(itemStateInt);
	}
		
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
