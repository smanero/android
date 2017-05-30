/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ActionsActionTable extends ItemsTable {

	private ActionsActionTable(){
		this.tableName = "acciones_accion";
		this.columns = new String[]{
			"accion",
			"orden",
			"accion_2"
		};
		this.textual = new boolean[]{
			false,
			false,
			false
		};
	}
}
