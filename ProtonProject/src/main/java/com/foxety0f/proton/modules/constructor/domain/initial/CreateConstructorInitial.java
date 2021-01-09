package com.foxety0f.proton.modules.constructor.domain.initial;

import java.util.List;

public class CreateConstructorInitial {

    private String guid;
    private Integer thread;
    private List<CreateConstructorReportColumn> columns;

    public String getGuid() {
	return guid;
    }

    public void setGuid(String guid) {
	this.guid = guid;
    }

    public Integer getThread() {
	return thread;
    }

    public void setThread(Integer thread) {
	this.thread = thread;
    }

    public List<CreateConstructorReportColumn> getColumns() {
	return columns;
    }

    public void setColumns(List<CreateConstructorReportColumn> columns) {
	this.columns = columns;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columns == null) ? 0 : columns.hashCode());
	result = prime * result + ((guid == null) ? 0 : guid.hashCode());
	result = prime * result + ((thread == null) ? 0 : thread.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CreateConstructorInitial other = (CreateConstructorInitial) obj;
	if (columns == null) {
	    if (other.columns != null)
		return false;
	} else if (!columns.equals(other.columns))
	    return false;
	if (guid == null) {
	    if (other.guid != null)
		return false;
	} else if (!guid.equals(other.guid))
	    return false;
	if (thread == null) {
	    if (other.thread != null)
		return false;
	} else if (!thread.equals(other.thread))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CreateConstructorInitial [guid=" + guid + ", thread=" + thread + ", columns=" + columns + "]";
    }

}
