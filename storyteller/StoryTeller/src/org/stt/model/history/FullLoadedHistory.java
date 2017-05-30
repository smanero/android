package org.stt.model.history;

import java.util.Hashtable;

/**
 * Representa un History en que todos los datos de Pages se cargan completamente
 * en memoria. De esa forma esta implementación no necesitará acceso a DAO una
 * vez bien informada.
 * 
 * @author BIHEALGA
 * 
 */
public class FullLoadedHistory extends History {

	protected Hashtable<StReference, Page> pageMap;
	protected Hashtable<StReference, PageDispacher> pageDispacherMap;

	/**
	 * Basic Constructor
	 */
	public FullLoadedHistory() {
		super();
	}

	/**
	 * Constructor that make a instance of private maps
	 * 
	 * @param pageNumber
	 *            Number of Pages that the history will be contains
	 * @param dispacherNumber
	 *            Number of PageDispacher than the history will be contains
	 */
	public FullLoadedHistory(int pageNumber, int dispacherNumber) {
		super();
		this.pageMap = new Hashtable<StReference, Page>(pageNumber);
		if (dispacherNumber != 0) {
			this.pageDispacherMap = new Hashtable<StReference, PageDispacher>(
					dispacherNumber);
		}
	}

	/**
	 * @param page
	 */
	public void putPage(Page page) {
		if (this.pageMap == null) {
			this.pageMap = new Hashtable<StReference, Page>();
		}
		this.pageMap.put(page.getId(), page);
	}

	/**
	 * @param dispacher
	 */
	public void putPageDispacher(PageDispacher dispacher) {
		if (this.pageDispacherMap == null) {
			this.pageDispacherMap = new Hashtable<StReference, PageDispacher>();
		}
		this.pageDispacherMap.put(dispacher.id, dispacher);
	}

	/**
	 * Acceso directo a Hashtable pageMap. {@inheritDoc}
	 */
	protected Page getPage(StReference pageId) {
		return this.pageMap.get(pageId);
	}

	/**
	 * Acceso directo a Hashtable pageDispacherMap. {@inheritDoc}
	 */
	protected PageDispacher getPageDispacher(StReference pageDispacherId) {
		return this.pageDispacherMap.get(pageDispacherId);
	}

	/**
	 * @param pageMap
	 *            the pageMap to set
	 */
	public void setPageMap(Hashtable<StReference, Page> pageMap) {
		this.pageMap = pageMap;
	}

	/**
	 * @param pageDispacherMap
	 *            the pageDispacherMap to set
	 */
	public void setPageDispacherMap(
			Hashtable<StReference, PageDispacher> pageDispacherMap) {
		this.pageDispacherMap = pageDispacherMap;
	}

}
