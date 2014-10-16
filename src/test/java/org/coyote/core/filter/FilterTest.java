package org.coyote.core.filter;

import org.coyote.core.domain.CarEntity;
import org.coyote.core.domain.ColorEntity; 
import org.coyote.core.domain.MarkEntity;
import org.coyote.core.domain.ModelEntity;
import org.coyote.core.persistence.query.filter.Filter;
import org.coyote.core.persistence.query.filter.QueryFilter;
import org.coyote.core.persistence.query.filter.QueryFilterFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test; 

public class FilterTest {
	
	private CarEntity carEntity;
	private QueryFilter queryFilter;
	
	@Before
	public void initialize() {
		
		carEntity = new CarEntity();
		carEntity.setId(1l);
		carEntity.setPrice(30000.00);
		
		carEntity.setColor(new ColorEntity());
		carEntity.getColor().setId(2l);
		carEntity.getColor().setName("blue");
		
		carEntity.setModel(new ModelEntity());
		carEntity.getModel().setMark(new MarkEntity());
		carEntity.getModel().getMark().setName("honda");
				
		queryFilter = QueryFilterFactory.create(carEntity);
		queryFilter.removeInvalidFilters();
		
	}

	@Test // Testa o id do carro
	public void teste01() {
		for (Filter filter : queryFilter.getFilters()) {
			if (filter.getName().equals("id"))
				Assert.assertEquals(1l, filter.getValue());
		}
		queryFilter.printFilters();
	}
	
	@Test // Testa o preço do carro
	public void teste02() {
		for (Filter filter : queryFilter.getFilters()) {
			if (filter.getName().equals("price"))
				Assert.assertEquals(30000.00, filter.getValue());
		}
	}
	
	@Test // Testa o id da cor
	public void teste03() {
		for (Filter filter : queryFilter.getFilters()) {
			if (filter.getName().equals("color.id"))
				Assert.assertEquals(2l, filter.getValue());
		}
	}
	
	@Test // Testa o nome da cor
	public void teste04() {
		for (Filter filter : queryFilter.getFilters()) {
			if (filter.getName().equals("color.name"))
				Assert.assertEquals("blue", filter.getValue());
		}
	}
	
	@Test // Testa o nome da marca
	public void teste05() {
		for (Filter filter : queryFilter.getFilters()) {
			if (filter.getName().equals("model.mark.name"))
				Assert.assertEquals("honda", filter.getValue());
		}
	}
}
