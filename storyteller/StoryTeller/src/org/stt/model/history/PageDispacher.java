package org.stt.model.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class PageDispacher {

	StReference id;
	private final List<StCase> caseList =  new ArrayList<StCase>();
	List<StAction> actionsList = Collections.emptyList();//acciones de inicio de pagina
	
	public StReference nextPage(Properties properties){
		final Iterator<StCase> it = caseList.iterator();
		StReference ref = null;
		while(it.hasNext() && ref != null){
			ref = it.next().checkCase();
		} 
		return ref;
	}
	
	public void addCase(Condition condition, StReference ref){
		caseList.add(new StCase(condition, ref));
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
}

/**
 * Condición que si se cumple se devuelve la referencia. Sino null.
 * Representa algo parecido a un Case de un switch.
 * Si la condición es null se considera que siempre cumple, como un caso default.
 * @author Gaizka
 *
 */
class StCase{
	private final Condition condition;
	private final StReference ref;
	
	public StCase(Condition condition, StReference ref){
		this.condition = condition;
		this.ref = ref;
	}
	
	public StReference checkCase(){
		if(condition == null || condition.match()){
			return ref;
		}else{
			return null;
		}
	}
}
