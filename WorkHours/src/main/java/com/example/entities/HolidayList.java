package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "HOLIDAY_LIST")
public class HolidayList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "YEAR")
    private String year;

    @Column(name = "HOLIDAY")
    private String holiday;
    
    @Column(name = "HOLIDAY_DAY")
    private String holidayDay;
    
    @Column(name = "HOLIDAY_DATE")
    private String holidayDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getHolidayDay() {
		return holidayDay;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDay(String holidayDay) {
		this.holidayDay = holidayDay;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "HolidayList [id=" + id + ", type=" + type + ", year=" + year + ", holiday=" + holiday + ", holidayDay="
				+ holidayDay + ", holidayDate=" + holidayDate + "]";
	}
    
}
