package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAVE_DETAILS")
public class LeaveDetails {
	
	@Id
	private String empId;
	
	private String empName;
	
	private String email;
	
	private String casual;
	
	private String earned;
	
	private String total;
	
	private String lastYearBalance;
	
	private String optional;
	
	private String compOff;
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCasual() {
		return casual;
	}

	public void setCasual(String casual) {
		this.casual = casual;
	}

	public String getEarned() {
		return earned;
	}

	public void setEarned(String earned) {
		this.earned = earned;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getLastYearBalance() {
		return lastYearBalance;
	}

	public void setLastYearBalance(String lastYearBalance) {
		this.lastYearBalance = lastYearBalance;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompOff() {
		return compOff;
	}

	public void setCompOff(String compOff) {
		this.compOff = compOff;
	}

	@Override
	public String toString() {
		return "LeaveDetails [empId=" + empId + ", empName=" + empName + ", email=" + email + ", casual=" + casual
				+ ", earned=" + earned + ", total=" + total + ", lastYearBalance=" + lastYearBalance + ", optional="
				+ optional + ", compOff=" + compOff + "]";
	}

}
