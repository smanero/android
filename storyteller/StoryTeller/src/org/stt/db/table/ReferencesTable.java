/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ReferencesTable extends ItemsTable{
	public ReferencesTable(){
		super();
		this.tableName = "referencias";
		this.columns = new String[]{
			"referencia",
			"tipo",
			"clave"
		};
		this.textual = new boolean[]{
			true,
			true,
			true
		};
	}
}
