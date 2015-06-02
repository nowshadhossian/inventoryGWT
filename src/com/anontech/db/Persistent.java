package com.anontech.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public class Persistent implements Serializable {

	private static final long serialVersionUID = 8946343210856227025L;

	private Date created;
	private Date updated;
	private Long createdBy;
	private Long updatedBy;

	public Persistent() {}
        
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Basic
	@Column(name = "created_by", columnDefinition = "bigint references users(id)", nullable = false, updatable = false)
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Basic
	@Column(name = "updated_by", columnDefinition = "bigint references users(id)")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Transient
	public Object[] getObjectArray() {
		return new Object[] {"Not Implemented"};
	}
}