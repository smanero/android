/**
 * 
 */
package org.stt.model.history;

import java.util.Collection;
import java.util.Collections;

/**
 * Representa una colección de acciones a realizar de forma condicional
 * Puede contener requisitos para funcionar.
 * @author Gaizka
 *
 */
public class StAction {
	//control de acciones concurrentes para evitar sobrecarga o bucles indefinidos
	private static final int CONCURRENCE_LIMIT = 50;
	private static int nCurrentActions = 0;

	private Collection<Condition> enableConditions = Collections.emptyList();
	private Collection<StOperation> operations = Collections.emptyList();
	private Collection<StAction> actions = Collections.emptyList();
	
	public boolean isEnabled(){
		return Condition.and(enableConditions);
	}
	
	/**
	 * Si habilitado ejecuta todas las operaciones vinculadas.
	 * Control de acciones concurrentes o en loop.
	 */
	public void shut(){
		if(this.isEnabled()){
			StAction.nCurrentActions++;
			if(StAction.nCurrentActions <0 || StAction.nCurrentActions > CONCURRENCE_LIMIT){
				throw new RuntimeException("Acciones concurrentes fuera de rango");
			}
			for(StOperation operacion : this.operations){
				operacion.shut();
			}
			for(StAction accion : this.actions){
				accion.shut();
			}
			StAction.nCurrentActions--;
		}
	}

	/**
	 * @return the enableConditions
	 */
	public Collection<Condition> getEnableConditions() {
		return enableConditions;
	}

	/**
	 * @param enableConditions the enableConditions to set
	 */
	public void setEnableConditions(Collection<Condition> enableConditions) {
		this.enableConditions = enableConditions;
	}

	/**
	 * @return the operations
	 */
	public Collection<StOperation> getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(Collection<StOperation> operations) {
		this.operations = operations;
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
}
