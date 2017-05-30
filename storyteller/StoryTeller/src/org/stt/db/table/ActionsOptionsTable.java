/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ActionsOptionsTable extends ItemsTable {

	public ActionsOptionsTable(){
		this.tableName = "acciones_opcion";
		this.columns = new String[]{
			"opcion",
			"orden",
			"accion"
		};
		this.textual = new boolean[]{
			false,
			false,
			false
		};
	}
}
