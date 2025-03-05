package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.CompOffRequest;
import com.example.entities.EmployeeDetails;
import com.example.entities.HolidayList;
import com.example.entities.LeaveDetails;
import com.example.entities.LeaveHistory;
import com.example.entities.UpdateLeaveRequest;
import com.example.wrapper.WorkHoursRestControllerWrapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/workhours")
//@CrossOrigin(origins = "http://localhost:3000")
public class WorkHoursRestController {

	private static Logger logger = LoggerFactory.getLogger(WorkHoursRestController.class);

	@Autowired
	private WorkHoursRestControllerWrapper restWrapper;

	@GetMapping("/getEmployee")
	public String getEmployeeByUsername(@RequestParam String username) {
		EmployeeDetails empDetails = restWrapper.getEmployeeByUsername(username);
		logger.info("empDetails:" + empDetails);
		if (empDetails != null) {
			// employeeDetails = new ObjectMapper().writeValueAsString(empDetails);
			return new Gson().toJson(empDetails);
		}
		return null;
	}

	@GetMapping("/getEmpEmailIds")
	public String getEmployeeEmailIds() {
		List<String> emailIds = restWrapper.getEmployeeEmailIds();
		logger.info("emailIds:" + emailIds);
		if (emailIds != null) {
			// employeeDetails = new ObjectMapper().writeValueAsString(empDetails);
			return new Gson().toJson(emailIds);
		}
		return null;
	}
	
	@GetMapping("/getLoginPassword")
	public String getLoginPassword(@RequestParam String username) {
		EmployeeDetails empDetails = restWrapper.getEmployeeByUsername(username);
		logger.info("empDetails:" + empDetails);
		if (empDetails != null) {
			String status = restWrapper.getLoginPassword(username);
			logger.info("status:" + status);
			return new Gson().toJson(empDetails);
		}
		return null;
	}

	@GetMapping("/getEmpIds")
	public String getEmpIds() {
		List<String> empIds = restWrapper.getEmpIds();
		logger.info("empIds:" + empIds);
		if (empIds != null) {
			// employeeDetails = new ObjectMapper().writeValueAsString(empDetails);
			return new Gson().toJson(empIds);
		}
		return null;
	}

	@GetMapping("/getLeaveDetails")
	public String getLeaveDetailsByEmpId(@RequestParam String username) {
		LeaveDetails leaveDetails = restWrapper.getLeaveDetails(username);
		logger.info("LeaveDetails:" + leaveDetails);
		if (leaveDetails != null) {
			// employeeDetails = new ObjectMapper().writeValueAsString(empDetails);
			return new Gson().toJson(leaveDetails);
		}
		return null;
	}

	@GetMapping("/getLeaveHistory")
	public String getLeaveHistory(@RequestParam String username) {
		List<LeaveHistory> leaveHistoryDetails = restWrapper.getLeaveHisDetails(username);
		logger.info("LeaveDetails:" + leaveHistoryDetails);
		if (leaveHistoryDetails != null) {
			return new Gson().toJson(leaveHistoryDetails);
		}
		return null;
	}
	
	@GetMapping("/getCompOffHistory")
	public String getCompOffHistory(@RequestParam String username) {
		List<CompOffRequest> compOffDetails = restWrapper.getCompOffHistory(username);
		logger.info("compOffDetails:" + compOffDetails);
		if (compOffDetails != null) {
			return new Gson().toJson(compOffDetails);
		}
		return null;
	}

	@GetMapping("/getRequestedLeave")
	public String getRequestedLeave(@RequestParam String username) {
		logger.info("*****Requested leave starts******");
		List<LeaveHistory> leaveHistoryDetails = restWrapper.getRequestedLeave(username);
		logger.info("LeaveDetails:" + leaveHistoryDetails);
		if (leaveHistoryDetails != null) {
			return new Gson().toJson(leaveHistoryDetails);
		} else {
			return null;
		}
	}

	@GetMapping("/getHolidayList")
	public String getHolidayList(@RequestParam String type) {
		logger.info("Holiday Type:" + type);
		List<HolidayList> holidayList = restWrapper.getHolidayList(type);
		logger.info("holidayList:" + holidayList);
		if (holidayList != null) {
			return new Gson().toJson(holidayList);
		}
		return null;
	}
	
	@PostMapping("/updateLeaveHistory")
	public void updateLeaveHistory(@RequestBody UpdateLeaveRequest request) {
		logger.info("request:" + request);
		Long id = request.getId();
		String action = request.getAction();
		if (action != null && !action.isEmpty() && action.equalsIgnoreCase("accept")) {
			restWrapper.updateLeaveHistory(id, "Approved");
			restWrapper.updateLeaveDetails(id);
		}
		if (action != null && !action.isEmpty() && action.equalsIgnoreCase("reject")) {
			restWrapper.updateLeaveHistory(id, "Rejected");
		}
	}
	
	@PostMapping("/updateCompOffHistory")
	public void updateCompOffHistory(@RequestBody UpdateLeaveRequest request) {
		logger.info("request:" + request);
		Long id = request.getId();
		String action = request.getAction();
		if (action != null && !action.isEmpty() && action.equalsIgnoreCase("accept")) {
			restWrapper.updateCompOffHistory(id, "Added");
			restWrapper.updateCompOffLeaveDetails(id);
		}
		if (action != null && !action.isEmpty() && action.equalsIgnoreCase("reject")) {
			restWrapper.updateLeaveHistory(id, "Cancelled");
		}
	}

	@PostMapping("/saveAppliedLeave")
	public void saveAppliedLeave(@RequestBody String leaveDetails) {
		logger.info("Applied Leave details:" + leaveDetails);
		restWrapper.saveLeaveHistory(leaveDetails);
	}
	
	@PostMapping("/saveCompOffHistory")
	public void saveCompOffHistory(@RequestBody String compOffDetails) {
		logger.info("Applied Comp-Off details:" + compOffDetails);
		restWrapper.saveCompOffHistory(compOffDetails);
	}
	
	@PostMapping("/updateEmployeeDetails")
	public void updateEmployeeDetails(@RequestBody String leaveDetails) {
		logger.info("Applied Leave details:" + leaveDetails);
		restWrapper.updateEmployeeDetails(leaveDetails);
	}
	
	@PostMapping("/saveBulkEmployeeDetails")
    public void saveBulkEmployeeeDetails(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ;
        }
        restWrapper.saveBulkEmployees(file);
    }
	
	@PostMapping("/saveBulkLeaveDetails")
    public void saveBulkLeaveDetails(@RequestParam("file") MultipartFile file) {
		logger.info("leave file:"+file);
        if (file.isEmpty()) {
            return ;
        }
        restWrapper.saveBulkLeaveDetails(file);
    }
	
	@PostMapping("/saveHolidayList")
    public void saveHolidayList(@RequestParam("file") MultipartFile file) {
		logger.info("holiday file:"+file);
        if (file.isEmpty()) {
            return ;
        }
        restWrapper.saveBulkHolidays(file);
    }
	
	@DeleteMapping("/deleteEmployee")
	public void deleteEmployeeUsingId(@RequestParam String empId) {
		restWrapper.deleteEmployeeUsingId(empId);
	}

	/*
	 * @PostMapping("/sendForgetPasswordEmail") public void
	 * sendEmailForForgetPassword(@RequestParam String email) {
	 * emailServiceWrapper.sendSimpleMessage(email); }
	 */
	
	
}
