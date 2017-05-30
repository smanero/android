/**
 * 
 */
package org.stt.db.table;

/**
 * @author Gaizka
 *
 */
public class ActionsDispacherTable extends ItemsTable{
	
	public ActionsDispacherTable(){
		this.tableName = "acciones_dispacher";
		this.columns = new String[]{
			"dispacher",
			"orden",
			"accion"
		};
		this.textual = new boolean[]{
			true,
			false,
			false
		};
	}
}
