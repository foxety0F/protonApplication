package com.foxety0f.proton.modules.reporting.domain.meta;

public class SourceMetaTable {

    private String schema;
    private String table;
    private String description;
    private Integer systemId;

    public String getSchema() {
	return schema;
    }

    public void setSchema(String schema) {
	this.schema = schema;
    }

    public String getTable() {
	return table;
    }

    public void setTable(String table) {
	this.table = table;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getSystemId() {
	return systemId;
    }

    public void setSystemId(Integer systemId) {
	this.systemId = systemId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((schema == null) ? 0 : schema.hashCode());
	result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
	result = prime * result + ((table == null) ? 0 : table.hashCode());
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
	SourceMetaTable other = (SourceMetaTable) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (schema == null) {
	    if (other.schema != null)
		return false;
	} else if (!schema.equals(other.schema))
	    return false;
	if (systemId == null) {
	    if (other.systemId != null)
		return false;
	} else if (!systemId.equals(other.systemId))
	    return false;
	if (table == null) {
	    if (other.table != null)
		return false;
	} else if (!table.equals(other.table))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SourceMetaTable [schema=" + schema + ", table=" + table + ", description=" + description + ", systemId="
		+ systemId + "]";
    }

}
