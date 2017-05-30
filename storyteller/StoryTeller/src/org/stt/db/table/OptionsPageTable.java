package org.stt.db.table;

public class OptionsPageTable extends ItemsTable {

	/**
	 * Constructor
	 */
	public OptionsPageTable(){
		this.tableName = "opciones_pagina";
		this.columns = new String[]{
			"pagina",
			"orden",
			"opcion"
		};
		this.textual = new boolean[]{
			true,
			false,
			false
		};
	}
}
