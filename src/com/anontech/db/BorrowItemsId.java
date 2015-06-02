package com.anontech.db;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BorrowItemsId implements Serializable{
	private Borrow borrow;
	private Items item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "borrow_id")
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
}
