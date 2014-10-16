package org.coyote.core.persistence.query.filter;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public enum OperatorType {

	AND("and"), //
	OR("or"), //
	EQUALS("="), //
	LIKE("like"), //
	DIFFERENT("<>"), //
	IS_NULL("is null"), //
	IS_NOT_NULL("is not null"), //
	OPEN("("), //
	CLOSE(")"), //
	GREATER(">"), //
	GREATER_OR_EQUAL(">="), //
	LESS("<"), //
	LESS_OR_EQUAL("<=");

	private String operator;

	private OperatorType(String operator) {
		this.operator = operator;
	}

	public String toString() {
		return operator;
	}

}