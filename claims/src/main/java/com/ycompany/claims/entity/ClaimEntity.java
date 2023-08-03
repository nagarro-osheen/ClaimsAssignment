package com.ycompany.claims.entity;

import com.ycompany.claims.enums.ClaimStatusEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ClaimEntity {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	String uuid;
	@Column
	String policyNumber;
	@Column
	String username;
	@Column
	BigDecimal amount;
	@Column
	boolean needApproval;
	@Column
	boolean approved;

	@Column
	String approver;

	@Column
	ClaimStatusEnum status;

	@Column
	String workflowProcessId;

	@Column
	String workflowApprovalTaskId;

	public String getWorkflowProcessId() {
		return workflowProcessId;
	}

	public void setWorkflowProcessId(String workflowProcessId) {
		this.workflowProcessId = workflowProcessId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isNeedApproval() {
		return needApproval;
	}

	public void setNeedApproval(boolean needApproval) {
		this.needApproval = needApproval;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public ClaimStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ClaimStatusEnum status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWorkflowApprovalTaskId() {
		return workflowApprovalTaskId;
	}

	public void setWorkflowApprovalTaskId(String workflowApprovalTaskId) {
		this.workflowApprovalTaskId = workflowApprovalTaskId;
	}
}
