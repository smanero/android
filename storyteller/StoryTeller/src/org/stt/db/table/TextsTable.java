/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class TextsTable extends ItemsTable {

	public TextsTable() {
		super();
		this.tableName = "textos";
		this.columns = new String[]{
			"texto",
			"orden",
			"esReferencia",
			"referencia",
			"cadena"
		};
		this.textual = new boolean[]{
			false,
			false,
			false,
			true,
			true
		};
	}
}
