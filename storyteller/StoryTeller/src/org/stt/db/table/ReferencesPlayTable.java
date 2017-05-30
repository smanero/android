/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ReferencesPlayTable extends ItemsTable {

	public ReferencesPlayTable(){
		this.tableName = "referencias_partida";
		this.columns = new String[]{
			"partida",
			"referencias",
			"valor"
		};
		this.textual = new boolean[]{
			false,
			true,
			true
		};
	}
}
