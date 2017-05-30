/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ConditionsOptionTable extends ItemsTable {

	public ConditionsOptionTable(){
		this.tableName = "condiciones_opcion";
		this.columns = new String[]{
			"opcion",
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
