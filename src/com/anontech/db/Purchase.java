package com.anontech.db;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "purchase")
public class Purchase extends Persistent{
	private Long id;
	private Double otherExpenses;
	private Boolean isForeignPurchase;
	private String lcNumber;
	private Date lcDate;
	private String airwaysBillNumber;
	private Double airportDuty;
	private Double airportDemurrage;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "purchase")
    @javax.persistence.SequenceGenerator(name = "purchase",
    sequenceName = "purchase_seq")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getOtherExpenses() {
		return otherExpenses;
	}
	public void setOtherExpenses(Double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}
	public Boolean getIsForeignPurchase() {
		return isForeignPurchase;
	}
	public void setIsForeignPurchase(Boolean isForeignPurchase) {
		this.isForeignPurchase = isForeignPurchase;
	}
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lc_date")
	public Date getLcDate() {
		return lcDate;
	}
	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}
	public String getAirwaysBillNumber() {
		return airwaysBillNumber;
	}
	public void setAirwaysBillNumber(String airwaysBillNumber) {
		this.airwaysBillNumber = airwaysBillNumber;
	}
	public Double getAirportDuty() {
		return airportDuty;
	}
	public void setAirportDuty(Double airportDuty) {
		this.airportDuty = airportDuty;
	}
	public Double getAirportDemurrage() {
		return airportDemurrage;
	}
	public void setAirportDemurrage(Double airportDemurrage) {
		this.airportDemurrage = airportDemurrage;
	}
}
