package com.springBoot.userDetails.DTO;

import com.springBoot.userDetails.model.Organization;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

public class UserDTO {

    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    private String password;

    private String onlineStatus;

    private Timestamp creationDate;

    private Date birthDate;

    public String getOrg_Id() {
        return org_Id;
    }

    public void setOrg_Id(String org_Id) {
        this.org_Id = org_Id;
    }

    private String org_Id;

    private Organization organization;
}
