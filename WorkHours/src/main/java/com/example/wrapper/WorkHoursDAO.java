package com.example.wrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.CompOffRequest;
import com.example.entities.EmployeeDetails;
import com.example.entities.HolidayList;
import com.example.entities.LeaveDetails;
import com.example.entities.LeaveHistory;
import com.example.utility.DAOUtil;

public class WorkHoursDAO {
	
	@Autowired
    private DAOUtil daoUtil;
	
	private String EMAIL_ID = "EMAIL_ID";
	
	private String TYPE = "TYPE";
	
	private String YEAR = "YEAR";
	
	private String ID = "ID";
	
	private String EMP_ID="EMP_ID";
	
	private String GET_EMPLOYEE = "select e.email, e.userName, e.employeeId, e.position, e.mobile, e.password from EmployeeDetails e where e.employeeId=:"+EMP_ID;
	
	private String GET_EMPLOYEE_EMAILS = "select e.email from EmployeeDetails e";
	
	private String GET_EMPLOYEE_IDS = "select e.employeeId from EmployeeDetails e";
	
	private String GET_HOLIDAY_LIST = "select l.holidayDay, l.holidayDate, l.holiday from HolidayList l where type=:"+TYPE+" and year=:"+YEAR; 
	
	private String GET_LEAVE_HISTORY_USING_ID = "select h.emailId, h.type, h.reportingTo, h.requestApprover, h.dates, h.status, h.employeeId, h.reason, h.empName, h.id, h.halfDay from LeaveHistory h where h.id=:"+ID;
	
	private String GET_COMP_OFF_REQUEST_USING_ID = "select h.emailId, h.id, h.reportingTo, h.requestApprover, h.dates, h.employeeId, h.reason, h.empName, h.status from CompOffRequest h where h.id=:"+ID;

	private String GET_LEAVE_HISTORY = "select h.emailId, h.type, h.reportingTo, h.requestApprover, h.dates, h.status, h.employeeId, h.reason, h.empName, h.id, h.halfDay from LeaveHistory h where h.emailId=:"+EMAIL_ID;
	
	private String GET_COMP_OFF_HISTORY = "select h.emailId, h.id, h.reportingTo, h.requestApprover, h.dates, h.status, h.employeeId, h.reason, h.empName from CompOffRequest h where h.emailId=:"+EMAIL_ID;

	private String GET_REQUESTED_LEAVE = "select h.empName, h.employeeId, h.type, h.dates, h.reason, h.id , h.halfDay from LeaveHistory h where h.status='Requested' and h.requestApprover=:"+EMAIL_ID;
	
	private String GET_USER_PASSWORD = "select e.email, e.userName, e.employeeId, e.position, e.mobile, e.password, e.dateOfBirth, e.domain, e.designation from EmployeeDetails e where e.email=:"+EMAIL_ID;
	
	private String GET_LEAVE_DETAILS = "select l.empName, l.casual, l.earned, l.lastYearBalance, l.optional, l.total, l.compOff, l.email, l.empId from LeaveDetails l where l.email=:"+EMAIL_ID;
	
	public List<Object[]> getEmployeeByUserName(String username) {
	    Map<String, Object> map = new HashMap<>();
	    map.put(EMAIL_ID, username);
	    return daoUtil.executeQuery(GET_USER_PASSWORD, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}
	
	public List<Object[]> getEmployee(String empId) {
	    Map<String, Object> map = new HashMap<>();
	    map.put(EMP_ID, empId);
	    return daoUtil.executeQuery(GET_EMPLOYEE, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}


	public List<Object[]> getLeaveDetails(String email) {
		 Map<String, Object> map = new HashMap<>();
		    map.put(EMAIL_ID, email);
		    return daoUtil.executeQuery(GET_LEAVE_DETAILS, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}
	
	public List<Object[]> getLeaveHistoryUsingId(Long id) {
		Map<String, Object> map = new HashMap<>();
	    map.put(ID, id);
	    return daoUtil.executeQuery(GET_LEAVE_HISTORY_USING_ID, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}

	public List<Object[]> getCompOffRequestUsingId(Long id) {
		Map<String, Object> map = new HashMap<>();
	    map.put(ID, id);
	    return daoUtil.executeQuery(GET_COMP_OFF_REQUEST_USING_ID, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}

	public List<Object[]> getLeaveHistory(String username) {
		Map<String, Object> map = new HashMap<>();
	    map.put(EMAIL_ID, username);
	    return daoUtil.executeQuery(GET_LEAVE_HISTORY, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}
	
	public List<Object[]> getCompOffHistory(String username) {
		Map<String, Object> map = new HashMap<>();
	    map.put(EMAIL_ID, username);
	    return daoUtil.executeQuery(GET_COMP_OFF_HISTORY, map, Object[].class);  // Adjusted to Object[] to hold name and password
	}
	
	@SuppressWarnings("static-access")
	public void saveLeaveHistory(LeaveHistory leavehistory) {
		daoUtil.saveEntity(leavehistory);
	}
	
	@SuppressWarnings("static-access")
	public void saveCompOffHistory(CompOffRequest compOffRequest) {
		daoUtil.saveEntity(compOffRequest);
	}

	public List<Object[]> getRequestedLeave(String email) {
		Map<String, Object> map = new HashMap<>();
	    map.put(EMAIL_ID, email);
	    return daoUtil.executeQuery(GET_REQUESTED_LEAVE, map, Object[].class);
	}


	public void updateLeaveHistory(LeaveHistory leaveHistory) {
		daoUtil.updateEntity(leaveHistory);
	}

	public void updateCompOffHistory(CompOffRequest compOffHistory) {
		daoUtil.updateEntity(compOffHistory);
	}

	public List<Object[]> getHolidayList(String type, String yearValue) { 
		Map<String, Object> map = new HashMap<>();
	    map.put(TYPE, type);
	    map.put(YEAR, yearValue);
	    return daoUtil.executeQuery(GET_HOLIDAY_LIST, map, Object[].class);
	}


	public List<String> getEmailIds() {
		 Map<String, Object> parameters = new HashMap<>(); // If no parameters are needed
	     return daoUtil.executeQuery(GET_EMPLOYEE_EMAILS, parameters, String.class);
	}


	public void updateLeaveDetails(LeaveDetails leaveDetails) {
		daoUtil.updateEntity(leaveDetails);
	}

	public void saveBulkLeave(List<LeaveDetails> leaveList) {
		daoUtil.saveBulkEntities(leaveList);
	}

	public void saveBulkEmployee(List<EmployeeDetails> employeeList) {
		daoUtil.saveBulkEntities(employeeList);
	}
	
	public void saveBulkHoliday(List<HolidayList> holidayList) {
		daoUtil.saveBulkEntities(holidayList);
	}


	public List<String> getEmpIds() {
		 Map<String, Object> parameters = new HashMap<>(); // If no parameters are needed
	     return daoUtil.executeQuery(GET_EMPLOYEE_IDS, parameters, String.class);
	}


	public void deleteEmployee(EmployeeDetails empDetails) {
		daoUtil.deleteEntity(empDetails);
	}

	public void updateEmployeeDetails(EmployeeDetails empDetails) {
		daoUtil.updateEntity(empDetails);
	}

}
