package org.coyote.core.persistence.query.filter.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class AttributeBean {

	private Collection<ColumnBean> columnBeans;
	private Collection<JoinColumnBean> joinColunmBeans;

	public void addColumn(ColumnBean columnBean) {
		if (columnBeans == null) {
			columnBeans = new ArrayList<ColumnBean>();
		}

		columnBeans.add(columnBean);
	}

	public void addJoinColumn(JoinColumnBean joinColunmBean) {
		if (joinColunmBeans == null) {
			joinColunmBeans = new ArrayList<JoinColumnBean>();
		}

		joinColunmBeans.add(joinColunmBean);
	}

}