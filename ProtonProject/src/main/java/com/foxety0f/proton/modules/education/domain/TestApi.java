package com.foxety0f.proton.modules.education.domain;

import java.util.Date;

public class TestApi {

    private Integer id;
    private String name;
    private Date datetime;
    private Boolean isActual;

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

    public Date getDatetime() {
	return datetime;
    }

    public void setDatetime(Date datetime) {
	this.datetime = datetime;
    }

    public Boolean getIsActual() {
	return isActual;
    }

    public void setIsActual(Boolean isActual) {
	this.isActual = isActual;
    }

    @Override
    public String toString() {
	return "TestApi [id=" + id + ", name=" + name + ", datetime=" + datetime + ", isActual=" + isActual + "]";
    }

}
