/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class CasesDispacherTable extends ItemsTable {

	public CasesDispacherTable(){
		this.tableName = "cases_dispacher";
		this.columns = new String[]{
			"dispacher",
			"orden",
			"condicion",
			"referencia"
		};
		this.textual = new boolean[]{
			true,
			false,
			false,
			true
		};
	}
}
