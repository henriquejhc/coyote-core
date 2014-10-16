package org.coyote.core.persistence.criteria;

public class JoinPath {

	private JoinType joinType;
	private String path;
	private String alias;

	public JoinPath(JoinType joinType, String path) {
		super();
		this.joinType = joinType;
		this.path = path;
		this.alias = this.path.substring(this.path.indexOf('.') + 1);
	}

	public JoinPath(JoinType joinType, String path, String alias) {
		super();
		this.joinType = joinType;
		this.path = path;
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}