package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.io.Serializable;

public class ValueSetDefinitionEntryInfo implements Serializable {
	
	private static final long serialVersionUID = 3914282027649631967L;
	
	private String entityCode;
	private String predicateGuid;
	private boolean isTransitive;

	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getPredicateGuid() {
		return predicateGuid;
	}
	public void setPredicateGuid(String predicateGuid) {
		this.predicateGuid = predicateGuid;
	}
	public boolean isTransitive() {
		return isTransitive;
	}
	public void setTransitive(boolean isTransitive) {
		this.isTransitive = isTransitive;
	}

}
