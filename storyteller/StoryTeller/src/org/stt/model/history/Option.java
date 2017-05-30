package org.stt.model.history;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Represent a button with an option.
 * If pressed, use followPageId for to know the next page.
 * @author BIHEALGA
 *
 */
public class Option {
	private StText texto;//lo que se muestra en la seleccion
	private List<Condition> visibleConditions = Collections.emptyList();
	private Collection<StAction> actions = Collections.emptyList();
	private StReference nextPageId;
	
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
	 * @return the visibleConditions
	 */
	public List<Condition> getVisibleConditions() {
		return visibleConditions;
	}
	/**
	 * @param visibleConditions the visibleConditions to set
	 */
	public void setVisibleConditions(List<Condition> visibleConditions) {
		this.visibleConditions = visibleConditions;
	}
	public String getDescription() {
		return this.texto.getText();
	}
	/**
	 * @deprecated
	 * @param description
	 */
	public void setDescription(String description) {
		this.texto = new StText(description);
	}
	/**
	 * Esta funcion se usará en Game.choose, 
	 * cuando se da por seleccionada la opción.
	 * Devuelve la siguiente Page.
	 * Las actions de la option se ejecutan antes de invocar esta función.
	 * @return next pageId to follow.
	 */
	public StReference followPageId() {
		return this.nextPageId;
	}
	
	
	/**
	 * Un option podría gracias a sobreescribir ésta función 
	 * ocultarse en alguna condición dependiente de las properties de Game. 
	 * @return false, if it must be hidden; true, in other way.
	 */
	public boolean isNotHidden(){
		return Condition.and(this.visibleConditions);
	}
	/**
	 * @return the actions
	 */
	public Collection<StAction> getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(Collection<StAction> actions) {
		this.actions = actions;
	}
	/**
	 * ejecuta todas las acciones disponibles vinculadas a la opcion.
	 * Usar antes de cambiar de página
	 */
	public void shutActions(){
		for(StAction action : this.actions){
			action.shut();
		}
	}

	/**
	 * 
	 * @return this.nextPageId
	 */
	public StReference getNextPageId() {
		return this.nextPageId;
	}

	/**
	 * 
	 * @param nextPageId
	 *            pageId
	 */
	public void setNextPageId(StReference nextPageId) {
		this.nextPageId = nextPageId;
	}
}
