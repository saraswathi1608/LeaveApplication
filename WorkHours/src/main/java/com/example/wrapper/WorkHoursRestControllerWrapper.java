package com.example.wrapper;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.*;
import com.example.utility.CommonsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.Authenticator;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.*;

public class WorkHoursRestControllerWrapper {
	
	private static Logger logger = LoggerFactory.getLogger(WorkHoursRestControllerWrapper.class);
	
	@Autowired
	private WorkHoursDAO workHoursDAO;
	
	public EmployeeDetails getEmployeeByUsername(String username) {
		try {
			List<Object[]> employeeObj = workHoursDAO.getEmployeeByUserName(username);
			logger.info("employeeObj:"+employeeObj);
			if(employeeObj!=null) {
				EmployeeDetails empDetails = new EmployeeDetails();
				for(Object[] empObj: employeeObj) {
					if(empObj[0]!=null && !CommonsUtil.isObjectEmpty(empObj[0]))
							empDetails.setEmail(empObj[0].toString());
					if(empObj[1]!=null && !CommonsUtil.isObjectEmpty(empObj[1]))
						empDetails.setUserName(empObj[1].toString());
					if(empObj[2]!=null && !CommonsUtil.isObjectEmpty(empObj[2]))
						empDetails.setEmployeeId(empObj[2].toString());
					if(empObj[3]!=null && !CommonsUtil.isObjectEmpty(empObj[3]))
						empDetails.setPosition(empObj[3].toString());
					if(empObj[4]!=null && !CommonsUtil.isObjectEmpty(empObj[4]))
						empDetails.setMobile(empObj[4].toString());
					if(empObj[5]!=null && !CommonsUtil.isObjectEmpty(empObj[5]))
						empDetails.setPassword(empObj[5].toString());
					if(empObj[6]!=null && !CommonsUtil.isObjectEmpty(empObj[6]))
						empDetails.setDateOfBirth(empObj[6].toString());
					if(empObj[7]!=null && !CommonsUtil.isObjectEmpty(empObj[7]))
						empDetails.setDomain(empObj[7].toString());
					if(empObj[8]!=null && !CommonsUtil.isObjectEmpty(empObj[8]))
						empDetails.setDesignation(empObj[8].toString());
					/*if (empObj[9] != null && !CommonsUtil.isObjectEmpty(empObj[9])) {
	                    empDetails.setPhotoUrl(empObj[9].toString());
	                }*/
				}
				return empDetails;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting employee details:",e);
		}
		return null;
	}
	
	public LeaveDetails getLeaveDetails(String email) {
		try {
			List<Object[]> leaveDetailsObj = workHoursDAO.getLeaveDetails(email);
			logger.info("leaveDetailsObj:"+leaveDetailsObj);
			if(leaveDetailsObj!=null) {
				LeaveDetails leaveDetails = formLeaveDetails(leaveDetailsObj);
				logger.info("leaveDetails:"+leaveDetails);
				return leaveDetails;
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting leave details:",e);
		}
		return null;
	}

	private LeaveDetails formLeaveDetails(List<Object[]> leaveDetailsObj) {
		if(leaveDetailsObj!=null) {
			LeaveDetails leaveDetails = new LeaveDetails();
			for(Object[] leaveObj: leaveDetailsObj) {
				if(leaveObj[0]!=null && !CommonsUtil.isObjectEmpty(leaveObj[0]))
					leaveDetails.setEmpName(leaveObj[0].toString());
				if(leaveObj[1]!=null && !CommonsUtil.isObjectEmpty(leaveObj[1]))
					leaveDetails.setCasual(leaveObj[1].toString());
				if(leaveObj[2]!=null && !CommonsUtil.isObjectEmpty(leaveObj[2]))
					leaveDetails.setEarned(leaveObj[2].toString());
				if(leaveObj[3]!=null && !CommonsUtil.isObjectEmpty(leaveObj[3]))
					leaveDetails.setLastYearBalance(leaveObj[3].toString());
				if(leaveObj[4]!=null && !CommonsUtil.isObjectEmpty(leaveObj[4]))
					leaveDetails.setOptional(leaveObj[4].toString());
				if(leaveObj[5]!=null && !CommonsUtil.isObjectEmpty(leaveObj[5]))
					leaveDetails.setTotal(leaveObj[5].toString());
				if(leaveObj[6]!=null && !CommonsUtil.isObjectEmpty(leaveObj[6]))
					leaveDetails.setCompOff(leaveObj[6].toString());
				if(leaveObj[7]!=null && !CommonsUtil.isObjectEmpty(leaveObj[7]))
					leaveDetails.setEmail(leaveObj[7].toString());
				if(leaveObj[8]!=null && !CommonsUtil.isObjectEmpty(leaveObj[8]))
					leaveDetails.setEmpId(leaveObj[8].toString());
			}
			return leaveDetails;
		}
		return null;
	}
	
	public List<LeaveHistory> getLeaveHisDetails(String username) {
		try {
			List<Object[]> leaveHisObject = workHoursDAO.getLeaveHistory(username);
			logger.info("leaveHisObject:"+leaveHisObject);
			if(leaveHisObject!=null && !leaveHisObject.isEmpty()) {
				List<LeaveHistory> leaveHistoryList = new ArrayList<>();
				for(Object[] leaveHisObj: leaveHisObject) {
					String dates = "";
					String halfDay = "";
					LeaveHistory leaveHistory = new LeaveHistory();
					if(leaveHisObj[0]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[0]))
						leaveHistory.setEmailId(leaveHisObj[0].toString());
					if(leaveHisObj[1]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[1]))
						leaveHistory.setType(leaveHisObj[1].toString());
					if(leaveHisObj[2]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[2]))
						leaveHistory.setReportingTo(leaveHisObj[2].toString());
					if(leaveHisObj[3]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[3]))
						leaveHistory.setRequestApprover(leaveHisObj[3].toString());
					if(leaveHisObj[4]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[4]))
						dates = leaveHisObj[4].toString();
					if(leaveHisObj[5]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[5]))
						leaveHistory.setStatus(leaveHisObj[5].toString());
					if(leaveHisObj[6]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[6]))
						leaveHistory.setEmployeeId(leaveHisObj[6].toString());
					if(leaveHisObj[7]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[7]))
						leaveHistory.setReason(leaveHisObj[7].toString());
					if(leaveHisObj[8]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[8]))
						leaveHistory.setEmpName(leaveHisObj[8].toString());
					if(leaveHisObj[9]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[9])) {
						String id = leaveHisObj[9].toString();
						leaveHistory.setId(Long.parseLong(id));
					}
					if(leaveHisObj[10]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[10]))
						halfDay = leaveHisObj[10].toString()+" - HalfDay";
					if(dates!=null && !dates.isEmpty() && halfDay!=null && !halfDay.isEmpty())
						dates = halfDay+" , "+dates;
					else if(halfDay!=null && !halfDay.isEmpty() && (dates==null || dates.isEmpty()))
						dates = halfDay;
					leaveHistory.setDates(dates);
					leaveHistoryList.add(leaveHistory);
				}
				return leaveHistoryList;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Execption occured while getting leave history:",e);
		}
		return null;
	}
	
	public List<CompOffRequest> getCompOffHistory(String username) {
		try {
			List<Object[]> compOffRequestObj = workHoursDAO.getCompOffHistory(username);
			logger.info("compOffRequestObj:"+compOffRequestObj);
			if(compOffRequestObj!=null && !compOffRequestObj.isEmpty()) {
				List<CompOffRequest> compOffRequestlist = new ArrayList<>();
				for(Object[] compOffObj: compOffRequestObj) {
					CompOffRequest compOffReq = new CompOffRequest();
					if(compOffObj[0]!=null && !CommonsUtil.isObjectEmpty(compOffObj[0]))
						compOffReq.setEmailId(compOffObj[0].toString());
					if(compOffObj[1]!=null && !CommonsUtil.isObjectEmpty(compOffObj[1])) {
						String id = compOffObj[1].toString();
						compOffReq.setId(Long.parseLong(id));
					}
					if(compOffObj[2]!=null && !CommonsUtil.isObjectEmpty(compOffObj[2]))
						compOffReq.setReportingTo(compOffObj[2].toString());
					if(compOffObj[3]!=null && !CommonsUtil.isObjectEmpty(compOffObj[3]))
						compOffReq.setRequestApprover(compOffObj[3].toString());
					if(compOffObj[4]!=null && !CommonsUtil.isObjectEmpty(compOffObj[4])) 
						compOffReq.setDates(compOffObj[4].toString());
					if(compOffObj[5]!=null && !CommonsUtil.isObjectEmpty(compOffObj[5]))
						compOffReq.setStatus(compOffObj[5].toString());
					if(compOffObj[6]!=null && !CommonsUtil.isObjectEmpty(compOffObj[6]))
						compOffReq.setEmployeeId(compOffObj[6].toString());
					if(compOffObj[7]!=null && !CommonsUtil.isObjectEmpty(compOffObj[7]))
						compOffReq.setReason(compOffObj[7].toString());
					if(compOffObj[8]!=null && !CommonsUtil.isObjectEmpty(compOffObj[8]))
						compOffReq.setEmpName(compOffObj[8].toString());
					compOffRequestlist.add(compOffReq);
				}
				return compOffRequestlist;
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Execption occured while getting compOffRequestlist history:",e);
		}
		return null;
	}
	
	public void saveLeaveHistory(String leaveDetails) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			LeaveHistory leaveHistory = objectMapper.readValue(leaveDetails, LeaveHistory.class);
			logger.info("Leave History :"+leaveHistory);
			if(leaveHistory!=null && leaveHistory.getEmailId()!=null &&!leaveHistory.getEmailId().isEmpty()) {
				List<Object[]> employeeObj = workHoursDAO.getEmployeeByUserName(leaveHistory.getEmailId());
				logger.info("employeeObj:"+employeeObj);
				if(employeeObj!=null) {
					for(Object[] empObj: employeeObj) {
						if(empObj[1]!=null && !CommonsUtil.isObjectEmpty(empObj[1])) {
							String empName =empObj[1].toString();
							leaveHistory.setEmpName(empName);
						}
						if(empObj[2]!=null && !CommonsUtil.isObjectEmpty(empObj[2])) {
							String empId = empObj[2].toString();
							leaveHistory.setEmployeeId(empId);
						}
						leaveHistory.setStatus("Requested");
					}
				}
			}
			workHoursDAO.saveLeaveHistory(leaveHistory);
			sendNotifyEmailToApprover(leaveHistory);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("Exception while Saving Apllied leave in leave history:",e);
		}
	}
	
	private void sendNotifyEmailToApprover(LeaveHistory leaveHistory) {
		List<Object[]> leaveDetailsObjList = workHoursDAO.getLeaveDetails(leaveHistory.getEmailId());
		if(leaveDetailsObjList!=null) {
			LeaveDetails leaveDetail = formLeaveDetails(leaveDetailsObjList);
			if(leaveDetail!=null) {
				sendEmailForLeaveRequest(leaveDetail, leaveHistory);
			}
		}
	}

	public void saveCompOffHistory(String compOffDetails) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CompOffRequest compOffRequest = objectMapper.readValue(compOffDetails, CompOffRequest.class);
			logger.info("compOffRequest History :"+compOffRequest);
			if(compOffRequest!=null && compOffRequest.getEmailId()!=null &&!compOffRequest.getEmailId().isEmpty()) {
				List<Object[]> employeeObj = workHoursDAO.getEmployeeByUserName(compOffRequest.getEmailId());
				logger.info("employeeObj:"+employeeObj);
				if(employeeObj!=null) {
					for(Object[] empObj: employeeObj) {
						if(empObj[1]!=null && !CommonsUtil.isObjectEmpty(empObj[1])) {
							String empName =empObj[1].toString();
							compOffRequest.setEmpName(empName);
						}
						if(empObj[2]!=null && !CommonsUtil.isObjectEmpty(empObj[2])) {
							String empId = empObj[2].toString();
							compOffRequest.setEmployeeId(empId);
						}
						compOffRequest.setStatus("Requested");
					}
				}
			}
			workHoursDAO.saveCompOffHistory(compOffRequest);
			sendNotifyEmailToApprover(compOffRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("Exception while Saving Apllied leave in leave history:",e);
		}
	}
	
	private void sendNotifyEmailToApprover(CompOffRequest compOffRequest) {

		//String[] toAddresses = {"vijayarangam@nmsworks.co.in","rebeka@nmsworks.co.in"};
		String[] toAddresses = {"charansaranya966@gmail.com"};
		String[] updatedToAddresses = new String[toAddresses.length + 1];
		System.arraycopy(toAddresses, 0, updatedToAddresses, 0, toAddresses.length);
		updatedToAddresses[toAddresses.length] = compOffRequest.getRequestApprover();
		
		String[] ccAddresses = compOffRequest.getReportingTo().split(",");
		String body = "Hi Team,\n\n \t"+compOffRequest.getEmpName()+"("+compOffRequest.getEmployeeId()+") requested a Comp-Off addition request. Plz consider a request.\n\n Regards,\nNMSWorks";
		
		String from = "saraswathir@nmsworks.co.in";
		String appPassword = "dyhdzqmfbfcnhskt";
		// SMTP server configuration for Office 365
        String host = "smtp.office365.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        try {
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, appPassword);
	            }
	        });
	        
	        MimeMessage message = new MimeMessage(session);
	        
	        for (String toAddress : updatedToAddresses) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }

            // Add multiple CC recipients
            for (String ccAddress : ccAddresses) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
            }
            
            message.setFrom(new InternetAddress(from));
            message.setSubject("Comp-Off AddOn Request");
            message.setText(body);
            Transport.send(message);
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}

	public void updateEmployeeDetails(String employeeDetails) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			EmployeeDetails empDetails = objectMapper.readValue(employeeDetails, EmployeeDetails.class);
			logger.info("empDetails :"+empDetails);
			if(empDetails!=null && empDetails.getEmail()!=null && !empDetails.getEmail().isEmpty()) {
				List<Object[]> details = workHoursDAO.getEmployeeByUserName(empDetails.getEmail());
				if(details!=null) {
					String password = "";
					for(Object[] empObj: details) {
						if(empObj.length>4)
							password = empObj[5].toString();
					}
					if(password!=null && !password.isEmpty()) {
						empDetails.setPassword(password);
						workHoursDAO.updateEmployeeDetails(empDetails);
					}
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("Exception while updateEmployeeDetails:",e);
		}
	}

	public List<LeaveHistory> getRequestedLeave(String email) {
		try {
			List<Object[]> leaveHisObject = workHoursDAO.getRequestedLeave(email);
			logger.info("leaveHisObject:"+leaveHisObject);
			if(leaveHisObject!=null && !leaveHisObject.isEmpty()) {
				List<LeaveHistory> leaveHisList = new ArrayList<>();
				for(Object[] leaveHisObj: leaveHisObject) {
					String dates = "";
					String halfDay = "";
					LeaveHistory leaveHistory = new LeaveHistory();
					if(leaveHisObj[0]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[0]))
						leaveHistory.setEmpName(leaveHisObj[0].toString());
					if(leaveHisObj[1]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[1]))
						leaveHistory.setEmployeeId(leaveHisObj[1].toString());
					if(leaveHisObj[2]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[2]))
						leaveHistory.setType(leaveHisObj[2].toString());
					if(leaveHisObj[3]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[3]))
						dates = leaveHisObj[3].toString();
					if(leaveHisObj[4]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[4]))
						leaveHistory.setReason(leaveHisObj[4].toString());
					if(leaveHisObj[5]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[5])) {
						String leaveHisId = leaveHisObj[5].toString();
						leaveHistory.setId(Long.parseLong(leaveHisId));
					}
					if(leaveHisObj[6]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[6]))
						halfDay = leaveHisObj[6].toString()+" - HalfDay";
					if(dates!=null && !dates.isEmpty() && halfDay!=null && !halfDay.isEmpty())
						dates = halfDay+" , "+dates;
					else if(halfDay!=null && !halfDay.isEmpty() && (dates==null || dates.isEmpty()))
						dates = halfDay;
					leaveHistory.setDates(dates);
					leaveHisList.add(leaveHistory);
				}
				return leaveHisList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while getting requested leave details:", e);
		}
		return null;
	}
	
	public void updateLeaveHistory(Long id, String action) {
		try {
			List<Object[]> leaveHisObject = workHoursDAO.getLeaveHistoryUsingId(id);
			logger.info("leaveHisObject:"+leaveHisObject);
			if(leaveHisObject!=null && !leaveHisObject.isEmpty()) {
				LeaveHistory leaveHistory = new LeaveHistory();
				for(Object[] leaveHisObj: leaveHisObject) {
					if(leaveHisObj[0]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[0]))
						leaveHistory.setEmailId(leaveHisObj[0].toString());
					if(leaveHisObj[1]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[1]))
						leaveHistory.setType(leaveHisObj[1].toString());
					if(leaveHisObj[2]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[2]))
						leaveHistory.setReportingTo(leaveHisObj[2].toString());
					if(leaveHisObj[3]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[3]))
						leaveHistory.setRequestApprover(leaveHisObj[3].toString());
					if(leaveHisObj[4]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[4]))
						leaveHistory.setDates(leaveHisObj[4].toString());
					if(leaveHisObj[5]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[5]))
						leaveHistory.setStatus(leaveHisObj[5].toString());
					if(leaveHisObj[6]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[6]))
						leaveHistory.setEmployeeId(leaveHisObj[6].toString());
					if(leaveHisObj[7]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[7]))
						leaveHistory.setReason(leaveHisObj[7].toString());
					if(leaveHisObj[8]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[8]))
						leaveHistory.setEmpName(leaveHisObj[8].toString());
					if(leaveHisObj[9]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[9])) {
						String leaveHisId = leaveHisObj[9].toString();
						leaveHistory.setId(Long.parseLong(leaveHisId));
					}
					if(leaveHisObj[10]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[10]))
						leaveHistory.setHalfDay(leaveHisObj[10].toString());
					
				}
				if(leaveHistory!=null && !CommonsUtil.isObjectEmpty(leaveHistory)) {
					leaveHistory.setStatus(action);
					workHoursDAO.updateLeaveHistory(leaveHistory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while updating leave history:",e);
		}
	}
	
	public void updateCompOffHistory(Long id, String action) {
		try {
			List<Object[]> compOffHisObject = workHoursDAO.getCompOffRequestUsingId(id);
			logger.info("compOffHisObject:"+compOffHisObject);
			if(compOffHisObject!=null && !compOffHisObject.isEmpty()) {
				CompOffRequest compOffHistory = new CompOffRequest();
				for(Object[] compOffObj: compOffHisObject) {
					if(compOffObj[0]!=null && !CommonsUtil.isObjectEmpty(compOffObj[0]))
						compOffHistory.setEmailId(compOffObj[0].toString());
					if(compOffObj[1]!=null && !CommonsUtil.isObjectEmpty(compOffObj[1])) {
						String leaveHisId = compOffObj[1].toString();
						compOffHistory.setId(Long.parseLong(leaveHisId));
					}
					if(compOffObj[2]!=null && !CommonsUtil.isObjectEmpty(compOffObj[2]))
						compOffHistory.setReportingTo(compOffObj[2].toString());
					if(compOffObj[3]!=null && !CommonsUtil.isObjectEmpty(compOffObj[3]))
						compOffHistory.setRequestApprover(compOffObj[3].toString());
					if(compOffObj[4]!=null && !CommonsUtil.isObjectEmpty(compOffObj[4]))
						compOffHistory.setDates(compOffObj[4].toString());
					if(compOffObj[5]!=null && !CommonsUtil.isObjectEmpty(compOffObj[5]))
						compOffHistory.setEmployeeId(compOffObj[5].toString());
					if(compOffObj[6]!=null && !CommonsUtil.isObjectEmpty(compOffObj[6]))
						compOffHistory.setReason(compOffObj[6].toString());
					if(compOffObj[7]!=null && !CommonsUtil.isObjectEmpty(compOffObj[7]))
						compOffHistory.setEmpName(compOffObj[7].toString());
				}
				if(compOffHistory!=null && !CommonsUtil.isObjectEmpty(compOffHistory)) {
					compOffHistory.setStatus(action);
					workHoursDAO.updateCompOffHistory(compOffHistory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while updating leave history:",e);
		}
	}

	public List<HolidayList> getHolidayList(String type) {
		try {
			int year = Year.now().getValue();
			String yearValue = String.valueOf(year);
			List<Object[]> holidayListObject = workHoursDAO.getHolidayList(type,yearValue);
			logger.info("holidayListObject:"+holidayListObject);
			if(holidayListObject!=null && !holidayListObject.isEmpty()) {
				List<HolidayList> list = new ArrayList<>();
 				for(Object[] holidayListObj: holidayListObject) {
					HolidayList holidayList= new HolidayList();
					if(holidayListObj[0]!=null && !CommonsUtil.isObjectEmpty(holidayListObj[0]))
						holidayList.setHolidayDay(holidayListObj[0].toString());
					if(holidayListObj[1]!=null && !CommonsUtil.isObjectEmpty(holidayListObj[1]))
						holidayList.setHolidayDate(holidayListObj[1].toString());
					if(holidayListObj[2]!=null && !CommonsUtil.isObjectEmpty(holidayListObj[2]))
						holidayList.setHoliday(holidayListObj[2].toString());
					list.add(holidayList);
				}
				return list;
			}
		} catch(Exception e){
			e.printStackTrace();
			logger.error("Exception while getting holiday list:",e);
		}
		return null;
	}

	public List<String> getEmployeeEmailIds() {
		try {
			List<String> emailIds = workHoursDAO.getEmailIds();
			logger.info("emailIds:"+emailIds);
			if(emailIds!=null && !emailIds.isEmpty())
				return emailIds;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting email id's:",e);
		}
		return null;
	}

	public void updateLeaveDetails(Long id) {
		try {
			List<Object[]> leaveHisObject = workHoursDAO.getLeaveHistoryUsingId(id);
			logger.info("leaveHisObject:"+leaveHisObject);
			if(leaveHisObject!=null && !leaveHisObject.isEmpty()) {
				String emailId ="";
				String type = "";
				String dates = "";
				String isHalfDay = "";
				for(Object[] leaveHisObj: leaveHisObject) {
					if(leaveHisObj[0]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[0]))
						emailId = leaveHisObj[0].toString();
					if(leaveHisObj[1]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[1]))
						type = leaveHisObj[1].toString();
					if(leaveHisObj[4]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[4]))
						dates = leaveHisObj[4].toString();
					if(leaveHisObj[10]!=null && !CommonsUtil.isObjectEmpty(leaveHisObj[10]))
						isHalfDay = leaveHisObj[10].toString();
				}
				updateLeaveDetailsUsingHistory(emailId, type, dates, isHalfDay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while updating leave details after getting approval:",e);
		}
	}
	
	public void updateCompOffLeaveDetails(Long id) {
		try {
			List<Object[]> compOffHisObject = workHoursDAO.getCompOffRequestUsingId(id);
			logger.info("compOffHisObject:"+compOffHisObject);
			if(compOffHisObject!=null && !compOffHisObject.isEmpty()) {
				String emailId ="";
				String type = "";
				String dates = "";
				String isHalfDay = "";
				for(Object[] compOffHisObj: compOffHisObject) {
					if(compOffHisObj[0]!=null && !CommonsUtil.isObjectEmpty(compOffHisObj[0]))
						emailId = compOffHisObj[0].toString();
					if(compOffHisObj[4]!=null && !CommonsUtil.isObjectEmpty(compOffHisObj[4]))
						dates = compOffHisObj[4].toString();
				}
				updateCompOffLeaveDetailsUsingId(emailId, dates);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while updating leave details after getting approval:",e);
		}
	}
	
	private void updateCompOffLeaveDetailsUsingId(String emailId , String dates) {
		if(!emailId.isEmpty() && emailId!=null) {
			LeaveDetails leaveDetails = getLeaveDetails(emailId);
			logger.info("leaveDetails using email:"+leaveDetails);
			List<String> dateList = new ArrayList<String>(Arrays.asList(dates.split(",")));
			if(leaveDetails!=null && !CommonsUtil.isObjectEmpty(leaveDetails)) {
				leaveDetails.setCompOff(addCompOffRequestedLeave(leaveDetails.getCompOff(), dateList.size()));
				leaveDetails.setTotal(addCompOffRequestedLeave(leaveDetails.getTotal(), dateList.size()));
				logger.info("Updated leave details:"+leaveDetails);
				workHoursDAO.updateLeaveDetails(leaveDetails);
			}
		}
	}

	private String addCompOffRequestedLeave(String value, int size) {
		if(value!=null && !value.isEmpty() && size>0) {
			Double leave = Double.valueOf(value);
			leave = leave + size;
			value = leave.toString();
		}
		return value;
	}

	private void updateLeaveDetailsUsingHistory(String emailId, String type, String dates, String isHalfDay) {
		if(!emailId.isEmpty() && emailId!=null && type!=null && !type.isEmpty()) {
			LeaveDetails leaveDetails = getLeaveDetails(emailId);
			logger.info("leaveDetails using email:"+leaveDetails);
			List<String> dateList = new ArrayList<String>(Arrays.asList(dates.split(",")));
			if(leaveDetails!=null && !CommonsUtil.isObjectEmpty(leaveDetails)) {
				if(type.equalsIgnoreCase("casual")) 
					leaveDetails.setCasual(removeRequestedLeaveFromCount(leaveDetails.getCasual(), dateList.size(), isHalfDay));
				else if(type.equalsIgnoreCase("earned")) 
					leaveDetails.setEarned(removeRequestedLeaveFromCount(leaveDetails.getEarned(), dateList.size(), isHalfDay));
				else if(type.equalsIgnoreCase("optional"))
					leaveDetails.setOptional(removeRequestedLeaveFromCount(leaveDetails.getOptional(), dateList.size(), isHalfDay));
				else if(type.equalsIgnoreCase("compOff"))
					leaveDetails.setCompOff(removeRequestedLeaveFromCount(leaveDetails.getCompOff(), dateList.size(), isHalfDay));
				else if(type.equalsIgnoreCase("lastYearBalance"))
					leaveDetails.setLastYearBalance(removeRequestedLeaveFromCount(leaveDetails.getLastYearBalance(), dateList.size(), isHalfDay));
				leaveDetails.setTotal(removeRequestedLeaveFromCount(leaveDetails.getTotal(), dateList.size(), isHalfDay));
				logger.info("Updated leave details:"+leaveDetails);
				workHoursDAO.updateLeaveDetails(leaveDetails);
			}
		}
	}
	
	private String removeRequestedLeaveFromCount(String value, int count, String isHalfDay) {
		logger.info("value:"+value);
		if(value!=null && !value.isEmpty()) {
			Double leave = Double.valueOf(value);
			logger.info("Leave:"+leave+" and count:"+count);
			if(leave>=count) {
				if(isHalfDay!=null && !isHalfDay.isEmpty() && isHalfDay!=null && !isHalfDay.isEmpty()) {
					if(count>1)
						leave = leave - (count);
					double result = leave - 0.5;
					leave = result;
				} else {
					leave = leave - count;
				}
			}
			value = String.valueOf(leave);
			logger.info("value ::"+value);
			if(value!=null && !value.isEmpty() && value.contains(".0"))
				value = value.replace(".0", " ").trim();
			logger.info("leave:"+value);
		}
		return value;
	}
	
	public void saveBulkEmployees(MultipartFile file) {
	    try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        List<EmployeeDetails> employeeList = new ArrayList<>();

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) { // Skip header row
	                continue;
	            }

	            // Skip empty rows
	            if (isRowEmpty(row)) {
	                break;
	            }

	            EmployeeDetails employee = new EmployeeDetails();
	            employee.setEmployeeId(getCellValue(row.getCell(0)));
	            employee.setUserName(getCellValue(row.getCell(1)));
	            employee.setEmail(getCellValue(row.getCell(2)));
	            employee.setMobile(getCellValue(row.getCell(3)));
	            employee.setPassword(getCellValue(row.getCell(4)));
	            employee.setPosition(getCellValue(row.getCell(5)));
	            logger.info("employee <- excel:"+employee);
	            employeeList.add(employee);
	        }

	        logger.info("Employee list size: " + employeeList.size());
	        if (employeeList != null && !employeeList.isEmpty()) {
	            workHoursDAO.saveBulkEmployee(employeeList);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error("Failed to upload and insert data");
	    }
	    return;
	}
	
	public void saveBulkLeaveDetails(MultipartFile file) {
	    try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        List<LeaveDetails> leaveDetailsList = new ArrayList<>();

	        int lastRowNum = sheet.getLastRowNum();
	        
	        for (int i = 1; i <= lastRowNum; i++) { // Start at 1 to skip the header row
	            Row row = sheet.getRow(i);

	            if (row == null || isRowEmpty(row)) {
	                break; // Skip if row is empty
	            }

	            LeaveDetails leaveDetails = new LeaveDetails();
	            leaveDetails.setEmpId(getCellValue(row.getCell(0)));
	            leaveDetails.setEmpName(getCellValue(row.getCell(1)));
	            leaveDetails.setEmail(getCellValue(row.getCell(2)));
	            leaveDetails.setCasual(getCellValue(row.getCell(3)));
	            leaveDetails.setEarned(getCellValue(row.getCell(4)));
	            leaveDetails.setTotal(getCellValue(row.getCell(5)));
	            leaveDetails.setLastYearBalance(getCellValue(row.getCell(6)));
	            leaveDetails.setOptional(getCellValue(row.getCell(7)));
	            leaveDetails.setCompOff(getCellValue(row.getCell(8)));
	            leaveDetailsList.add(leaveDetails);
	        }

	        logger.info("Final leaveDetailsList size: " + leaveDetailsList.size());
	        
	        if (!leaveDetailsList.isEmpty()) {
	            workHoursDAO.saveBulkLeave(leaveDetailsList);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error("Failed to upload and insert data", e);
	    }
	}

	private boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != CellType.BLANK) {
	            return false;
	        }
	    }
	    return true;
	}

	
	public void saveBulkHolidays(MultipartFile file) {
		try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<HolidayList> holidayList = new ArrayList<>();
            int year = Year.now().getValue();
			String yearValue = String.valueOf(year);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                
                if (row == null || isRowEmpty(row)) {
	                break; // Skip if row is empty
	            }
                
                HolidayList holiday = new HolidayList();
                holiday.setType(getCellValue(row.getCell(0)));
                holiday.setHolidayDate(getCellValue(row.getCell(1)));
                holiday.setHolidayDay(getCellValue(row.getCell(2)));
                holiday.setHoliday(getCellValue(row.getCell(3)));
                holiday.setYear(yearValue);
                logger.info("holiday <- excel"+holiday);
                holidayList.add(holiday);
            }
            logger.info("Holiday list size:"+holidayList.size());
            if(holidayList!=null && !holidayList.isEmpty()) {
            	workHoursDAO.saveBulkHoliday(holidayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to upload and insert data");
        }
		return ;
	}
	
	public String getCellValue(Cell cell) {
	    if (cell == null) {
	        return "";
	    }
	    if (cell.getCellType() == CellType.STRING) {
	        return cell.getStringCellValue();
	    } else if (cell.getCellType() == CellType.NUMERIC) {
	        double numericValue = cell.getNumericCellValue();
	        // Check if the number is effectively an integer
	        if (numericValue == Math.floor(numericValue)) {
	            // It's a whole number, return as an integer string
	            return String.valueOf((int) numericValue);
	        } else {
	            // It's a decimal, return the plain string representation
	            return BigDecimal.valueOf(numericValue).toPlainString();
	        }
	    } else {
	        return "";
	    }
	}

	public List<String> getEmpIds() {
		try {
			List<String> empIds = workHoursDAO.getEmpIds();
			logger.info("empIds:"+empIds);
			if(empIds!=null && !empIds.isEmpty())
				return empIds;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting emp id's:",e);
		}
		return null;
	}

	public void deleteEmployeeUsingId(String empId) {
		try {
			if(empId!=null && !empId.isEmpty()) {
				List<Object[]> employeeObj = workHoursDAO.getEmployee(empId);
				logger.info("employeeObj:"+employeeObj);
				if(employeeObj!=null) {
					EmployeeDetails empDetails = new EmployeeDetails();
					for(Object[] empObj: employeeObj) {
						if(empObj[0]!=null && !CommonsUtil.isObjectEmpty(empObj[0]))
								empDetails.setEmail(empObj[0].toString());
						if(empObj[1]!=null && !CommonsUtil.isObjectEmpty(empObj[1]))
							empDetails.setUserName(empObj[1].toString());
						if(empObj[2]!=null && !CommonsUtil.isObjectEmpty(empObj[2]))
							empDetails.setEmployeeId(empObj[2].toString());
						if(empObj[3]!=null && !CommonsUtil.isObjectEmpty(empObj[3]))
							empDetails.setPosition(empObj[3].toString());
						if(empObj[4]!=null && !CommonsUtil.isObjectEmpty(empObj[4]))
							empDetails.setMobile(empObj[4].toString());
						if(empObj[5]!=null && !CommonsUtil.isObjectEmpty(empObj[5]))
							empDetails.setPassword(empObj[5].toString());
					}
					workHoursDAO.deleteEmployee(empDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting Emp id's:",e);
		}
	}

	public void updateNewLeaveDetails() {
		List<String> emailIds = workHoursDAO.getEmailIds();
		logger.info("emailIds size:"+emailIds.size());
		if(emailIds!=null && !emailIds.isEmpty()) {
			for(String email: emailIds) {
				LeaveDetails leaveDetails = getLeaveDetails(email);
				leaveDetails.setCasual("10");
				leaveDetails.setEarned("20");
				leaveDetails.setLastYearBalance(setLastYearBalance(leaveDetails.getLastYearBalance(), leaveDetails.getEarned()));
				leaveDetails.setTotal(setTotalLeave(leaveDetails.getTotal(), 30));
				leaveDetails.setOptional("2");
				workHoursDAO.updateLeaveDetails(leaveDetails);
			}
		}
		
	}
	
	private String setLastYearBalance(String lastYearBalance, String earned) {
		if(lastYearBalance!=null && !lastYearBalance.isEmpty() && earned!=null && !earned.isEmpty()) {
			int earnedLeave = Integer.valueOf(earned);
			if(earnedLeave>=10)
				earnedLeave = 10;
			int lastYrLeave = Integer.valueOf(lastYearBalance)+earnedLeave;
			return String.valueOf(lastYrLeave);
		}
		return null;
	}

	private String setTotalLeave(String value, int count) {
		if(value!=null && !value.isEmpty()) {
			int leave = Integer.valueOf(value);
			leave = leave+count;
			value = String.valueOf(leave);
			return value;
		}
		return null;
	}

	public String getLoginPassword(String username) {
		List<Object[]> employeeObj = workHoursDAO.getEmployeeByUserName(username);
		logger.info("employeeObj:"+employeeObj);
		if(employeeObj!=null) {
			String password = "";
			String empName = "";
			for(Object[] empObj: employeeObj) {
				if(empObj[1]!=null && !CommonsUtil.isObjectEmpty(empObj[1])) {
					empName = empObj[1].toString();
				}
				if(empObj[5]!=null && !CommonsUtil.isObjectEmpty(empObj[5])) {
					password = empObj[5].toString();
				}
			}
			if(password!=null && !password.isEmpty()) {
				String status = sendForgotPasswordEmail(username, password, empName);
				return status;
			}
		}
		return null;
	}
	
	private String sendForgotPasswordEmail(String username, String password, String empName) {
		String body = "Hi "+empName+",\n\n \tYour Work-Hours login Password is "+password+".\n\n Regards,\nNMSWorks";
		
		String from = "saraswathir@nmsworks.co.in";
		String appPassword = "dyhdzqmfbfcnhskt";
		// SMTP server configuration for Office 365
        String host = "smtp.office365.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        try {
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, appPassword);
	            }
	        });
        
       
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
            message.setSubject("Password Remainder");
            message.setText(body);
            Transport.send(message);
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "Failed";
        }
		return "Success";
	}

	private void sendEmailForLeaveRequest(LeaveDetails leaveDetail, LeaveHistory leaveHistory) {
		
		//String[] toAddresses = {"vijayarangam@nmsworks.co.in","rebeka@nmsworks.co.in"};
		String[] toAddresses = {"charansaranya966@gmail.com"};
		String[] updatedToAddresses = new String[toAddresses.length + 1];
		System.arraycopy(toAddresses, 0, updatedToAddresses, 0, toAddresses.length);
		updatedToAddresses[toAddresses.length] = leaveHistory.getRequestApprover();
		
		String[] ccAddresses = leaveHistory.getReportingTo().split(",");
		String body = "<p>Hi Team,</p>" +
	              "<p> Due to "+leaveHistory.getReason()+", "+ leaveDetail.getEmpName() +" (" + leaveDetail.getEmpId() + ") will not available for work on "+leaveHistory.getDates()+". Please consider the request.</p>" +
	              "<p>Regards,<br>NMSWorks</p>";
		/*String body = "<p>Hi Team,</p>" +
	              "<p>" + leaveDetail.getEmpName() + " (" + leaveDetail.getEmpId() + ") requested a leave. Please consider the request.</p>" +
	              "<ul>" +
	              "<li>Total Leave Balance: " + leaveDetail.getTotal() + "</li>" +
	              "<li>Earned Leave Available: " + leaveDetail.getEarned() + "</li>" +
	              "<li>Casual Leave Available: " + leaveDetail.getCasual() + "</li>" +
	              "<li>Comp-Off Available: " + leaveDetail.getCompOff() + "</li>" +
	              "<li>Optional Leave Available: " + leaveDetail.getOptional() + "</li>" +
	              "<li>Last Year Leave Balance: " + leaveDetail.getLastYearBalance() + "</li>" +
	              "</ul>" +
	              "<p>Regards,<br>NMSWorks</p>";*/
		
		String from = "saraswathir@nmsworks.co.in";
		String appPassword = "dyhdzqmfbfcnhskt";
		// SMTP server configuration for Office 365
        String host = "smtp.office365.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        try {
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, appPassword);
	            }
	        });
	        
	        MimeMessage message = new MimeMessage(session);
	        
	        for (String toAddress : updatedToAddresses) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }

            // Add multiple CC recipients
            for (String ccAddress : ccAddresses) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
            }
            
            message.setFrom(new InternetAddress(from));
            message.setSubject("Leave Request From Work-Hours");
            message.setContent(body, "text/html");
            Transport.send(message);
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
/*	public static void main(String[] args) {
        final String username = "saraswathir@nmsworks.co.in";
        final String password = "dyhdzqmfbfcnhskt"; // Use your Microsoft account password or App Password

     // SMTP server configuration for Office 365
        String host = "smtp.office365.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gdevika@nmsworks.co.in"));
            message.setSubject("JAVA MAIL");
            message.setText("Hi Devika G,\n \n\tThis is a system generated message from Java code. \n \nRegards, \nSaraswathi R");
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }*/

}
