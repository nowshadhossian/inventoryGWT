package com.anontech.db;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoice")
public class Invoice extends Persistent {
	private long id;
	private Date buyingDate;
	private String invoiceNumber;
	private Vendor vendor;
	private UserDetails boughtBy;
	private Purchase purchase;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "invoice")
    @javax.persistence.SequenceGenerator(name = "invoice",
    sequenceName = "invoice_seq")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "buying_date")
	public Date getBuyingDate() {
		return buyingDate;
	}
	public void setBuyingDate(Date buyingDate) {
		this.buyingDate = buyingDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id")
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boughtby_id")
	public UserDetails getBoughtBy() {
		return boughtBy;
	}
	public void setBoughtBy(UserDetails boughtBy) {
		this.boughtBy = boughtBy;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purchase_id")
	public Purchase getPurchase() {
		return purchase;
	}
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
}
