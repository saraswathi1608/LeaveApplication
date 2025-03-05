package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name="LEAVE_HISTORY")
public class LeaveHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String type;
	
	private String dates;
	
	private String reportingTo;
	
	private String requestApprover;
	
	private String empName;
	
	private String emailId;
	
	private String employeeId;
	
	private String status;
	
	private String reason;
	
	private String halfDay;

	public String getHalfDay() {
		return halfDay;
	}

	public void setHalfDay(String halfDay) {
		this.halfDay = halfDay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getRequestApprover() {
		return requestApprover;
	}

	public void setRequestApprover(String requestApprover) {
		this.requestApprover = requestApprover;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LeaveHistory [id=" + id + ", type=" + type + ", dates=" + dates + ", reportingTo=" + reportingTo
				+ ", requestApprover=" + requestApprover + ", empName=" + empName + ", emailId=" + emailId
				+ ", employeeId=" + employeeId + ", status=" + status + ", reason=" + reason + ""
						+ ", halfDay=" + halfDay + "]";
	}
	
}
