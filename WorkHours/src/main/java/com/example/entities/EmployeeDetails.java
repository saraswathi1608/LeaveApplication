package com.example.entities;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "DOMAIN")
    private String domain;

    @Column(name = "DESIGNATION")
    private String designation;

   /* @Lob
    @Column(name = "PHOTOURL", columnDefinition = "BLOB")
    private byte[] photoUrl;*/

    // Getters and Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

/*    public String getPhotoUrlAsBase64() {
        if (this.photoUrl != null) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(this.photoUrl);
        }
        return null;
    }


    public void setPhotoUrl(String photoUrl) {
        if (photoUrl != null) {
            // Check if the photoUrl contains a prefix (e.g., "data:image/jpeg;base64,")
            String base64Image = photoUrl.contains(",") ? photoUrl.split(",")[1] : photoUrl;
            
            try {
                // Decode the base64 string into a byte array
                this.photoUrl = Base64.getDecoder().decode(base64Image);
            } catch (IllegalArgumentException e) {
                // Log the error if the string is not a valid base64
                System.err.println("Invalid base64 string: " + base64Image);
                throw e; // Re-throw the exception to indicate the failure
            }
        } else {
            this.photoUrl = null; // Handle case where photoUrl is null
        }
    }*/


    @Override
    public String toString() {
        return "EmployeeDetails [employeeId=" + employeeId + ", email=" + email + ", userName=" + userName
                + ", password=" + password + ", dateOfBirth=" + dateOfBirth + ", position=" + position + ", mobile="
                + mobile + ", domain=" + domain + ", designation=" + designation + "]";
    }
}
