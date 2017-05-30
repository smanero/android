/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class DispachersTable extends ItemsTable {

	public DispachersTable(){
		this.tableName = "dispacher";
		this.columns = new String[]{
			"dispacher"
		};
		this.textual = new boolean[]{
			true
		};
	}
}
