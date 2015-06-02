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
import javax.persistence.Transient;

@Entity
@Table(name = "requisition")
public class Requisition extends Persistent{
	
	private Long id;
	private Date requestedDate;
	private Date deliveryDate;
	private String placeToTake;
	private Department department;
	private User requestedBy;
	private PurposeEnum purpose;
	private Integer purposeInt;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "requisition")
    @javax.persistence.SequenceGenerator(name = "requisition",
    sequenceName = "requisition_seq")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requested_date")
	public Date getRequestedDate() {
		return requestedDate;
	}
	
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name = "place_to_take", length = 128)
	public String getPlaceToTake() {
		return placeToTake;
	}
	public void setPlaceToTake(String placeToTake) {
		this.placeToTake = placeToTake;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requested_by")
	public User getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(User requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	@Column(name = "purpose", nullable = false, columnDefinition = "smallint")
	public Integer getPurposeInt() {
		if (purpose != null) {
            purposeInt = purpose.toInt();
        }
        return purposeInt;
	}
	public void setPurposeInt(Integer purposeInt) {
		this.purposeInt = purposeInt;
		purpose = PurposeEnum.fromInt(purposeInt);
	}

	@Transient
	public PurposeEnum getPurpose() {
		return purpose;
	}
	public void setPurpose(PurposeEnum purpose) {
		this.purpose = purpose;
	}
}
