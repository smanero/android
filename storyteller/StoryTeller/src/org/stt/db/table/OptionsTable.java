/**
 * 
 */
package org.stt.db.table;


/**
 * @author Gaizka
 *
 */
public class OptionsTable extends ItemsTable {

	public OptionsTable() {
		super();
		this.tableName = "opciones";
		this.columns = new String[]{
			"opcion",
			"texto",
			"referencia"
		};
		this.textual = new boolean[]{
				false,
				false,
				true
		};
	}
	
}
