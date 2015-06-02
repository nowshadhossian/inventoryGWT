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
@Table(name = "borrow")
public class Borrow extends Persistent{
	private Long id;
	private User issuer;
	private PurposeEnum purpose;
	private Integer purposeInt;
	private Date outgoingDate;
	private Date incomingDate;
	private String address;
	private String phone;
	private String note;
	private Department department;
	private Requisition requisition;
	private User reporter;
	private User videoJournalist;
	private User receivedBy;
	private User approvedBy;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "borrow")
    @javax.persistence.SequenceGenerator(name = "borrow",
    sequenceName = "borrow_seq")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "issuer_id")
	public User getIssuer() {
		return issuer;
	}
	public void setIssuer(User issuer) {
		this.issuer = issuer;
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
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "outgoing_date")
	public Date getOutgoingDate() {
		return outgoingDate;
	}
	public void setOutgoingDate(Date outgoingDate) {
		this.outgoingDate = outgoingDate;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "incoming_date")
	public Date getIncomingDate() {
		return incomingDate;
	}
	public void setIncomingDate(Date incomingDate) {
		this.incomingDate = incomingDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	@JoinColumn(name = "requisition_id")
	public Requisition getRequisition() {
		return requisition;
	}
	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reporter_id")
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "video_journalist_id")
	public User getVideoJournalist() {
		return videoJournalist;
	}
	public void setVideoJournalist(User videoJournalist) {
		this.videoJournalist = videoJournalist;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "received_by_id")
	public User getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(User receivedBy) {
		this.receivedBy = receivedBy;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "approved_by_id")
	public User getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}
	
}
