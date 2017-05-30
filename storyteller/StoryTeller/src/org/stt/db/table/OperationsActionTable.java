/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class OperationsActionTable extends ItemsTable {

	public OperationsActionTable(){
		this.tableName = "operaciones_accion";
		this.columns = new String[]{
			"accion",
			"orden",
			"operacion"
		};
		this.textual = new boolean[]{
			false,
			false,
			false
		};
	}
}
