/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ConditionsTable extends ItemsTable {

	public ConditionsTable(){
		this.tableName = "condiciones";
		this.columns = new String[]{
			"condicion",
			"tipo_condicion",
			"referencia_1",
			"referencia_2"
		};
		this.textual = new boolean[]{
			false,
			true,
			true,
			true
		};
	}
}
