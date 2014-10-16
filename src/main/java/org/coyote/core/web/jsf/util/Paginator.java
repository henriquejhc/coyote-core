package org.coyote.core.web.jsf.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.coyote.core.web.jsf.IManagedBean;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
@Deprecated
public class Paginator implements IManagedBean, Serializable {

	private static final long serialVersionUID = 1L;

	private final String METHOD_NAME_FIND_DEFAULT = "find";
	private final String METHOD_NAME__COUNT_DEFAULT = "count";
	private final int REGISTER_NUMBER_PAGE_DEFAULT = 10;

	private List<SelectItem> pages;

	private int currentPage = 1;
	private int lastPage;
	private long registerNumber;
	private int registerNumberForPage;

	private IManagedBean managedBean;
	private String nameMethodFind;
	private String nameMethodCount;

	private Boolean renderedAll;

	public Paginator(IManagedBean managedBean) {
		this.managedBean = managedBean;
		this.nameMethodFind = METHOD_NAME_FIND_DEFAULT;
		this.nameMethodCount = METHOD_NAME__COUNT_DEFAULT;
		this.registerNumberForPage = REGISTER_NUMBER_PAGE_DEFAULT;
	}

	public Paginator(IManagedBean managedBean, String nameMethodFind, String nameMethodCount) {
		this.managedBean = managedBean;
		this.nameMethodFind = nameMethodFind;
		this.nameMethodCount = nameMethodCount;
		this.registerNumberForPage = REGISTER_NUMBER_PAGE_DEFAULT;
	}

	public Paginator(IManagedBean managedBean, String nameMethodFind, String nameMethodCount, int registerNumberForPage) {
		this.managedBean = managedBean;
		this.nameMethodFind = nameMethodFind;
		this.nameMethodCount = nameMethodCount;
		this.registerNumberForPage = registerNumberForPage;
	}

	/**
	 * Ir para a próxima página se for possível
	 * 
	 * @param event
	 */
	public void nextPage(ActionEvent event) {
		if (canCallNext()) {
			this.currentPage++;
			executeCount();
			executeFind();
		}
	}

	/**
	 * Volta para a página anterior se for possível.
	 * 
	 * @param event
	 */
	public void previousPage(ActionEvent event) {
		if (canReturn()) {
			this.currentPage--;
			executeCount();
			executeFind();
		}
	}

	/**
	 * Ir para última página se possível.
	 * 
	 * @param event
	 */
	public void goLastPage(ActionEvent event) {
		if (canCallNext()) {
			this.currentPage = this.lastPage;
			executeCount();
			executeFind();
		}
	}

	/**
	 * Voltar para a primeira página se for possível.
	 * 
	 * @param event
	 */
	public void previousFirstPage(ActionEvent event) {
		if (canReturn()) {
			this.currentPage = 1;
			executeCount();
			executeFind();
		}
	}

	/**
	 * Avança ou volta para uma determinada página, sendo que o número da página
	 * deve ser passado por parêmetro pela request com o nome de "paginaParaIr".
	 * 
	 * @param event
	 */
	public void goPage(ActionEvent event) {
		if (this.currentPage >= 0 && this.currentPage <= this.lastPage) {
			executeCount();
			executeFind();
		}
	}

	/**
	 * Atualiza a quantidade de registros e os dados que estão sendo mostrados.
	 */
	public void update() {
		initPage();
		executeCount();
		executeFind();
	}

	/**
	 * Executa a contagem, e para isso é chamado o método do managed bean..
	 */
	public void executeCount() {
		try {

			Method methodCount = managedBean.getClass().getMethod(nameMethodCount);
			Long totalResult = (Long) methodCount.invoke(managedBean);

			if (totalResult != this.registerNumber) {
				this.registerNumber = totalResult;
				calculateLastPage();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executa a busca, e para isso é chamado o método do managed bean.
	 */
	public void executeFind() {
		try {
			Method methodFind = managedBean.getClass().getMethod(nameMethodFind, Integer.class, Integer.class);
			methodFind.invoke(managedBean, (this.currentPage - 1) * this.registerNumberForPage, this.registerNumberForPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calcula o número da última página.
	 */
	private void calculateLastPage() {

		if ((registerNumber % registerNumberForPage) == 0)
			this.lastPage = (int) (this.registerNumber / this.registerNumberForPage);
		else
			this.lastPage = (int) (this.registerNumber / this.registerNumberForPage) + 1;

		if (this.currentPage > this.lastPage) {

			if (this.lastPage > 1)
				this.currentPage = this.lastPage;
			else
				this.currentPage = 1;

		}

		if (this.registerNumber <= 1)
			this.renderedAll = false;
		else
			this.renderedAll = true;

		updatePages();
	}

	private void updatePages() {

		this.pages = new ArrayList<SelectItem>();

		for (int i = 1; i <= this.lastPage; i++) {
			this.pages.add(new SelectItem(i, i + ""));
		}

	}

	public void updateNumberForPage() {
		initPage();
		this.registerNumber = 0;
		goPage(null);
	}

	public void updatePage() {
		goPage(null);
	}

	/**
	 * 
	 * @return o número da página que está sendo visualizada.
	 */
	public int getCurrentpage() {
		return this.currentPage;
	}

	/**
	 * 
	 * @return o número da última página.
	 */
	public int getLastPage() {
		return this.lastPage;
	}

	/**
	 * 
	 * @return o número de registros.
	 */
	public long getRegisterNumber() {
		return this.registerNumber;
	}

	/**
	 * 
	 * @return a número de registros exibidos por página.
	 */
	public int getRegisterNumberForPage() {
		return this.registerNumberForPage;
	}

	/**
	 * 
	 * @return true se não existirem registros.
	 */
	public boolean isEmpty() {
		return this.registerNumber == 0;
	}

	/**
	 * 
	 * @return true se existem mais páginas após a que está sendo visualizada.
	 */
	public boolean canCallNext() {
		return this.currentPage < this.lastPage;
	}

	/**
	 * 
	 * @return true se existem mais páginas antes da que está sendo visualizada.
	 */
	public boolean canReturn() {
		return this.currentPage > 1;
	}

	public void initPage() {
		this.currentPage = 1;
	}

	public void setRegisterNumberForPage(int number) {
		this.registerNumberForPage = number;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public List<SelectItem> getPages() {
		return pages;
	}

	public void setPages(List<SelectItem> pages) {
		this.pages = pages;
	}

	public Boolean getRenderedAll() {
		return renderedAll;
	}

	public void setRenderedAll(Boolean renderedAll) {
		this.renderedAll = renderedAll;
	}

	public void testeAcao(ActionEvent actionEvent) {
		System.out.println("Teste");
	}

}