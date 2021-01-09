package com.foxety0f.proton.modules.constructor.domain.web;

import java.util.List;

public class ConstructorRelationTablesWeb {

    private List<ConstructorTablesRelations> relations;
    private List<ConstructorTables> tables;

    public List<ConstructorTablesRelations> getRelations() {
	return relations;
    }

    public void setRelations(List<ConstructorTablesRelations> relations) {
	this.relations = relations;
    }

    public List<ConstructorTables> getTables() {
	return tables;
    }

    public void setTables(List<ConstructorTables> tables) {
	this.tables = tables;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((relations == null) ? 0 : relations.hashCode());
	result = prime * result + ((tables == null) ? 0 : tables.hashCode());
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
	ConstructorRelationTablesWeb other = (ConstructorRelationTablesWeb) obj;
	if (relations == null) {
	    if (other.relations != null)
		return false;
	} else if (!relations.equals(other.relations))
	    return false;
	if (tables == null) {
	    if (other.tables != null)
		return false;
	} else if (!tables.equals(other.tables))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ConstructorRelationTablesWeb [relations=" + relations + ", tables=" + tables + "]";
    }

}
