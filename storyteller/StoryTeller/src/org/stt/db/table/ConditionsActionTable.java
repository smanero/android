/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ConditionsActionTable extends ItemsTable {

	public ConditionsActionTable(){
		this.tableName = "condiciones_accion";
		this.columns = new String[]{
			"accion",
			"orden",
			"condicion"
		};
		this.textual = new boolean[]{
			false,
			false,
			false
		};
	}
}
