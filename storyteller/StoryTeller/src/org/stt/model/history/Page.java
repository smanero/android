package org.stt.model.history;

import java.util.Collections;
import java.util.List;


/**
 * @author BIHEALGA
 *
 */
public class Page {

	private StReference id;
	protected StText texto;
	List<Option> checkOptionList = Collections.emptyList();
	List<StAction> actionsList = Collections.emptyList();//acciones de inicio de pagina
	private String location;
	
	/**
	 * @return the id
	 */
	public StReference getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(StReference id) {
		this.id = id;
	}
	/**
	 * @return the textHistory
	 */
	public String getTextHistory() {
		return this.texto.getText();
	}
	/**
	 * @deprecated
	 * @param textHistory the textHistory to set
	 */
	public void setTextHistory(String textHistory) {
		if(this.texto !=null){
			this.texto.clear();
		}
		this.texto = new StText(textHistory);
	}
	/**
	 * 
	 * @return this.optionList
	 */
	public List<Option> getOptionList(){
		return this.checkOptionList;
	}
	/**
	 * 
	 * @param optionList to set
	 */
	public void setOptionList(List<Option> optionList){
		this.checkOptionList = optionList;
	}
	/**
	 * @return the texto
	 */
	public StText getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(StText texto) {
		this.texto = texto;
	}
	/**
	 * Ejecuta todas las opciones disponibles vinculadas a la pagina
	 * 
	 */
	public void shutActions(){
		for(StAction action : this.actionsList){
			action.shut();
		}
	}
	/**
	 * @return the actionsList
	 */
	public List<StAction> getActionsList() {
		return actionsList;
	}
	/**
	 * @param actionsList the actionsList to set
	 */
	public void setActionsList(List<StAction> actionsList) {
		this.actionsList = actionsList;
	}
	/**
	 * @return the location
	 */
	public final String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public final void setLocation(String location) {
		this.location = location;
	}
	
	
}
