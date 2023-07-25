package com.ycompany.claims.dto;

import java.util.Map;

public class WorkflowProcessStartVariablesDto {
	Map<String, Map<String, Object>> variables;

	public Map<String, Map<String, Object>> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Map<String, Object>> variables) {
		this.variables = variables;
	}
}
