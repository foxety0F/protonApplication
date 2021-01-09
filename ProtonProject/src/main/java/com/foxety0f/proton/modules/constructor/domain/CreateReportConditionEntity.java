package com.foxety0f.proton.modules.constructor.domain;

import java.util.List;

public class CreateReportConditionEntity {

    private Integer columnId;
    private Integer operationId;
    private String customName;
    private List<ReportingConditionsParamsMppg> params;

    public Integer getColumnId() {
	return columnId;
    }

    public void setColumnId(Integer columnId) {
	this.columnId = columnId;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public String getCustomName() {
	return customName;
    }

    public void setCustomName(String customName) {
	this.customName = customName;
    }

    public List<ReportingConditionsParamsMppg> getParams() {
	return params;
    }

    public void setParams(List<ReportingConditionsParamsMppg> params) {
	this.params = params;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
	result = prime * result + ((customName == null) ? 0 : customName.hashCode());
	result = prime * result + ((operationId == null) ? 0 : operationId.hashCode());
	result = prime * result + ((params == null) ? 0 : params.hashCode());
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
	CreateReportConditionEntity other = (CreateReportConditionEntity) obj;
	if (columnId == null) {
	    if (other.columnId != null)
		return false;
	} else if (!columnId.equals(other.columnId))
	    return false;
	if (customName == null) {
	    if (other.customName != null)
		return false;
	} else if (!customName.equals(other.customName))
	    return false;
	if (operationId == null) {
	    if (other.operationId != null)
		return false;
	} else if (!operationId.equals(other.operationId))
	    return false;
	if (params == null) {
	    if (other.params != null)
		return false;
	} else if (!params.equals(other.params))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CreateReportConditionEntity [columnId=" + columnId + ", operationId=" + operationId + ", customName="
		+ customName + ", params=" + params + "]";
    }

}
