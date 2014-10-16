package org.coyote.core.persistence.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.coyote.core.persistence.Persistence;
import org.coyote.core.persistence.model.EntityBean;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public abstract class AbstractPersistence<T extends EntityBean> implements Serializable, Persistence<T> {

	private static final long serialVersionUID = 1L;

	protected EntityManager entityManager;
	protected Class<T> entityClass;
	
	public AbstractPersistence() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractPersistence(Class entityClass) {
		super();
		this.entityClass = entityClass;
	}

	public Long count(Map<String, String> filtersEqualGrid, List<Filter> otherFilters, List<JoinPath> associationPaths) {

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> lazyLoadList(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filterLikeGrid, List<Filter> otherFilters, List<JoinPath> associationPaths) throws Exception {

		// CriteriaModel criteriaModel = new CriteriaModel();

		List<CriteriaModel> criteriaModelList = new ArrayList<CriteriaModel>();

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery criteria = criteriaBuilder.createQuery(entityClass);
		
		Root entityRoot = criteria.from(entityClass);
		criteria.select(entityRoot);

		for (CriteriaModel criteriaModel : criteriaModelList) {

			Path path = entityRoot.get((SingularAttribute) criteriaModel.getPropertie());
			Object value = criteriaModel.getValue();

			// criteriaBuilder.like(arg0, arg1)

			criteria.where(criteriaBuilder.equal(path, value));
		}






		// "brown"));

		// List<Person> people = em.createQuery(criteria).getResultList();
		
		//
		
		
		
		
		
		
		// /
		// /

		//

		//
		// Criteria criteriaa = this.createLazyLoadQuery(sortField, sortOrder,
		// filterLikeGrid, otherFilters, associationPaths);
		// return
		// criteriaa.setFirstResult(first).setMaxResults(pageSize).list();

		return null;
	}

	// public Criteria createLazyLoadQuery(String sortField, boolean sortOrder,
	// Map<String, String> filterLikeGrid, List<Filter> otherFilters,
	// List<JoinPath> associationPaths) {
	// Criteria criteria = this.entityManager.createCriteria(Object.class);
	// if (sortField != null && !sortField.isEmpty()) {
	// if (sortOrder) {
	// criteria.addOrder(Order.asc(sortField));
	// } else {
	// criteria.addOrder(Order.desc(sortField));
	// }
	// }
	// this.createAssociations(criteria, associationPaths);
	// this.addFilters(criteria, filterLikeGrid);
	// this.addFilters(criteria, otherFilters);
	// return criteria;
	// }

}