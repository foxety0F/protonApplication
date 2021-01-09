package com.foxety0f.proton.modules.constructor.domain;

/**
 * @author foxety0F
 * @category Create new Report
 * @description This class used for create new report
 * @param title - report name
 * @param description - report description
 * @param owner - user id of creator report
 * @param workspaceId - workspace id (default 100)
 * @param threadId - thread id for find core table
 * @param columns - columns create information
 * @param conditions - conditions create information
 * */
public class CreateReportEntity {

    private String title;
    private String description;
    private Long owner;
    private Integer workspaceId;
    private Integer threadId;
    private CreateReportColumnEntity columns;
    private CreateReportConditionEntity conditions;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Long getOwner() {
	return owner;
    }

    public void setOwner(Long owner) {
	this.owner = owner;
    }

    public Integer getWorkspaceId() {
	return workspaceId;
    }

    public void setWorkspaceId(Integer workspaceId) {
	this.workspaceId = workspaceId;
    }

    public Integer getThreadId() {
	return threadId;
    }

    public void setThreadId(Integer threadId) {
	this.threadId = threadId;
    }

    public CreateReportColumnEntity getColumns() {
	return columns;
    }

    public void setColumns(CreateReportColumnEntity columns) {
	this.columns = columns;
    }

    public CreateReportConditionEntity getConditions() {
	return conditions;
    }

    public void setConditions(CreateReportConditionEntity conditions) {
	this.conditions = conditions;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columns == null) ? 0 : columns.hashCode());
	result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((owner == null) ? 0 : owner.hashCode());
	result = prime * result + ((threadId == null) ? 0 : threadId.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	result = prime * result + ((workspaceId == null) ? 0 : workspaceId.hashCode());
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
	CreateReportEntity other = (CreateReportEntity) obj;
	if (columns == null) {
	    if (other.columns != null)
		return false;
	} else if (!columns.equals(other.columns))
	    return false;
	if (conditions == null) {
	    if (other.conditions != null)
		return false;
	} else if (!conditions.equals(other.conditions))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (owner == null) {
	    if (other.owner != null)
		return false;
	} else if (!owner.equals(other.owner))
	    return false;
	if (threadId == null) {
	    if (other.threadId != null)
		return false;
	} else if (!threadId.equals(other.threadId))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	if (workspaceId == null) {
	    if (other.workspaceId != null)
		return false;
	} else if (!workspaceId.equals(other.workspaceId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CreateReportEntity [title=" + title + ", description=" + description + ", owner=" + owner
		+ ", workspaceId=" + workspaceId + ", threadId=" + threadId + ", columns=" + columns + ", conditions="
		+ conditions + "]";
    }

}
