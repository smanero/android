/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class OperationsTable extends ItemsTable {

	public OperationsTable(){
		this.tableName = "operaciones";
		this.columns = new String[]{
			"operacion",
			"tipo_operacion",
			"referencia_1",
			"referencia_2",
			"referencia_3"
		};
		this.textual = new boolean[]{
			false,
			true,
			true,
			true,
			true
		};
	}
}
