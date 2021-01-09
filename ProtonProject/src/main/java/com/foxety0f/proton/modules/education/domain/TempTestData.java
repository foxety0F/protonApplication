package com.foxety0f.proton.modules.education.domain;

import java.util.Date;

public class TempTestData {

    private Integer id;
    private String name;
    private String groupOne;
    private String groupTwo;
    private Date dateAdd;
    private String toString;
    private Integer numGroupOne;
    private Integer numGroupTwo;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getGroupOne() {
	return groupOne;
    }

    public void setGroupOne(String groupOne) {
	this.groupOne = groupOne;
    }

    public String getGroupTwo() {
	return groupTwo;
    }

    public void setGroupTwo(String groupTwo) {
	this.groupTwo = groupTwo;
    }

    public Date getDateAdd() {
	return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
	this.dateAdd = dateAdd;
    }

    public void generateToString() {
	this.toString = toString();
    }

    public Integer getNumGroupOne() {
	return numGroupOne;
    }

    public void setNumGroupOne(Integer numGroupOne) {
	this.numGroupOne = numGroupOne;
    }

    public Integer getNumGroupTwo() {
	return numGroupTwo;
    }

    public void setNumGroupTwo(Integer numGroupTwo) {
	this.numGroupTwo = numGroupTwo;
    }

    @Override
    public String toString() {
	return "TempTestData [id=" + id + ", name=" + name + ", groupOne=" + groupOne + ", groupTwo=" + groupTwo
		+ ", dateAdd=" + dateAdd + "]";
    }

    
    public void hashCodeGroupOne() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((groupOne == null) ? 0 : groupOne.hashCode());
	this.numGroupOne = result;
    }
    
    public void hashCodeGroupTwo() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((groupTwo == null) ? 0 : groupTwo.hashCode());
	this.numGroupTwo = result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TempTestData other = (TempTestData) obj;
	if (groupOne == null) {
	    if (other.groupOne != null)
		return false;
	} else if (!groupOne.equals(other.groupOne))
	    return false;
	return true;
    }

}
