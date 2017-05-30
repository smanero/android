/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ActionsTable extends ItemsTable {

	public ActionsTable(){
		this.tableName = "acciones";
		this.columns = new String[]{
				"accion"
		};
	this.textual = new boolean[]{
		false
	};
}
}
