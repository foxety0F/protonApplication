package com.foxety0f.proton.modules.employees.domain;

import java.util.Date;

public class EmployeesInformation {

	private Integer idEmployee;
	private String login;
	private Boolean isActive;
	private Integer idGroup;
	private String groupName;
	private Integer titelId;
	private String titelName;
	private Date startDate;
	private Date endDate;
	private String pcNumber;
	private String placeNumber;
	private String ipAddress;
	private Boolean isActualRow;
	private Integer userId;
	private Integer idRow;
	private String firstName;
	private String surname;

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getTitelId() {
		return titelId;
	}

	public void setTitelId(Integer titelId) {
		this.titelId = titelId;
	}

	public String getTitelName() {
		return titelName;
	}

	public void setTitelName(String titelName) {
		this.titelName = titelName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPcNumber() {
		return pcNumber;
	}

	public void setPcNumber(String pcNumber) {
		this.pcNumber = pcNumber;
	}

	public String getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(String placeNumber) {
		this.placeNumber = placeNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Boolean getIsActualRow() {
		return isActualRow;
	}

	public void setIsActualRow(Boolean isActualRow) {
		this.isActualRow = isActualRow;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "EmployeesInformation [idEmployee=" + idEmployee + ", login=" + login + ", isActive=" + isActive
				+ ", idGroup=" + idGroup + ", groupName=" + groupName + ", titelId=" + titelId + ", titelName="
				+ titelName + ", startDate=" + startDate + ", endDate=" + endDate + ", pcNumber=" + pcNumber
				+ ", placeNumber=" + placeNumber + ", ipAddress=" + ipAddress + ", isActualRow=" + isActualRow
				+ ", userId=" + userId + ", idRow=" + idRow + ", firstName=" + firstName + ", surname=" + surname + "]";
	}

}
