/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class PagesTable extends ItemsTable {

	public PagesTable() {
		super();
		this.tableName = "paginas";
		this.columns = new String[]{
			"pagina",
			"texto",
			"lugar"
		};
		this.textual = new boolean[]{
			true,
			false,
			true
		};
	}
}
