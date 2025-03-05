package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "COMP_OFF_REQUEST")
public class CompOffRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String dates;
	
	private String reportingTo;
	
	private String requestApprover;
	
	private String empName;
	
	private String emailId;
	
	private String employeeId;
	
	private String status;
	
	private String reason;

	public Long getId() {
		return id;
	}

	public String getDates() {
		return dates;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public String getRequestApprover() {
		return requestApprover;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public void setRequestApprover(String requestApprover) {
		this.requestApprover = requestApprover;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "CompOffRequest [id=" + id + ", dates=" + dates + ", reportingTo=" + reportingTo + ", requestApprover="
				+ requestApprover + ", empName=" + empName + ", emailId=" + emailId + ", employeeId=" + employeeId
				+ ", status=" + status + ", reason=" + reason + "]";
	}
	
}
