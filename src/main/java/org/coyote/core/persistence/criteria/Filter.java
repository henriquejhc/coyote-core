package org.coyote.core.persistence.criteria;

public class Filter {

	private String field;
	private FilterType type;
	private Object value;

	public Filter(String field, Object value, FilterType type) {
		this.field = field;
		this.type = type;
		this.value = value;
	}

	public Filter(String field, Object value) {
		this(field, value, FilterType.EQUAL);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public FilterType getType() {
		return type;
	}

	public void setType(FilterType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}