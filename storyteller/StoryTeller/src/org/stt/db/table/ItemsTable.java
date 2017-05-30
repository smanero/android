/**
 * 
 */
package org.stt.db.table;

import org.stt.db.LoadedBean;

import android.database.Cursor;


/**
 * @author Gaizka
 *
 */
public abstract class ItemsTable {
	//clave primaria de la historia en curso
	//fundamental para que funcionen las cargas de las diversas tablas
	//ya que la historia está contenida en todas las claves primarias
	public static Integer historiaId;

	protected String tableName;
	//cada columna, su nombre
	protected String[] columns;
	// si cada columna es textual en el mismo orden que columns
	protected boolean[] textual;
	
	/**
	 * Para comprobar agilmente si existe la enésima columna.
	 * @param n n-esima comenzando por 0.
	 * @return true si no es nulo y está declarada
	 */
//	public boolean existsColumnN(int n){
//		return this.columns != null && this.columns.length > n;
//	}
	/**
	 * Usual concatenación en la construcción de un filtro.
	 * Añade al sb una nueva condición de filtro basada en la columna indicada por su índice.
	 * @param sb
	 * @param columns
	 * @param args
	 * @param index
	 */
	private void usualCat(StringBuilder sb, Object args, int index){
		sb.append(" AND ").append(this.columns[index]).append('=');
		if(textual[index]){
			sb.append('?');
		}else{
			sb.append(args);
		}
	}
	
	/**
	 * Para consultas donde se esperan obtener todos los elementos de la tabla
	 * @return típicamente "historia=historiaId"
	 */
	public String selectionFull(){
		return "historia=" + historiaId.toString();
	}
	/**
	 * Construye una sentencia dinámicamente.
	 * En una relacional debería sacar todos los registros relacionados.
	 * En una como textos, todos los textos del indicado.
	 * @param args argumentos de la sentencia
	 * @return ejemplo en tabla acciones_opcion: historia=historiaId AND opcion=args[0]
	 */
	public String selectionRelated(Object args){
		final int szsb = 60;//tamaño por defecto estimado por exceso
		StringBuilder sb = new StringBuilder(szsb)
		.append("historia=").append(ItemsTable.historiaId);
		this.usualCat(sb, args, 0);
		return sb.toString();
	}
	/**
	 * Construye una sentencia dinámicamente.
	 * Criterio por ID devolviendo único registro.
	 * Está pensado para tablas que no tengan campo orden (que no implican relación o que no sean de texto
	 * @param args argumento principal de la sentencia, llamado id
	 * @return ejemplo en tabla acciones_opcion: historia=historiaId AND opcion=args[0]
	 */
	public String selectionById(Object args){
		final int szsb = 70;//tamaño por defecto estimado por exceso
		StringBuilder sb = new StringBuilder(szsb)
		.append("historia=").append(historiaId);
		this.usualCat(sb, args, 0);
		return sb.toString();
	}
	/**
	 * Cuando un selector mete ? esta otra función devuelve el argumento que corresponde.
	 * Se espera que se use para consultas ById donde la key es textual.
	 * @param args mismos argumentos que para la función del selector
	 * @return el primer argumento si hay y si la primera columna es textual
	 */
	public String[] selectionRelatedArgs(Object args){
		if(this.textual[0] && args != null){
			return new String[]{args.toString()};
		}else{
			return null;
		}
	}

	/**
	 * @return the tableName
	 */
	public final String getTableName() {
		return tableName;
	}

	/**
	 * @return the columns
	 */
	public final String[] getColumns() {
		return columns;
	}

	/**
	 * 
	 * @return the orderBy para consultas tipo Related,
	 * Para consultas tipo Full si se desea ordenar habría que poner por delante la primera columna
	 */
	public final String getOrderByRelated() {
		return "orden";//valor más habitual para los ordenables que se consultan con Related
		//para una consulta tipo Full habría que añadir la primera columna al orderBy, pero sí sirve así para una tipo Related
	}

	/**
	 * @return the textual
	 */
	public final boolean isTextual() {
		return textual[0];
	}
	
	/**
	 * El método carga toda la información de la tupla contenida en la posición actual del cursor.
	 * El primer que formará parte de la pk lo pondrá en la key.
	 * El resto en values.
	 * @param cursor
	 * @return
	 */
	public final LoadedBean getLoadedBean(Cursor cursor){
		final LoadedBean lb = new LoadedBean();
		final int colIndex = cursor.getColumnIndexOrThrow(this.columns[0]);
		if(this.textual[0]){
			lb.key = cursor.getString(colIndex);
		}else{
			lb.key = Integer.valueOf(cursor.getInt(colIndex));
		}
		lb.values = this.getValueLoaderBean(cursor);
		return lb;
	}
	/**
	 * Conseguimos cada dato de la consulta incluido en la ubicación actual del cursor.
	 * @param cursor en una posición con dato
	 * @return Objeto del modelo representado por la tabla
	 */
	public Object[] getValueLoaderBean(Cursor cursor) {
		final Object[] resultado = new Object[this.columns.length -1];
		int colIndexSql = 0, colIndexTable = 0;
		for(int it=0; it < resultado.length;  it++){
			colIndexTable = it+1;//el indice para los elementos de itemTable es uno major que los valores de LoadedBean
			colIndexSql = cursor.getColumnIndexOrThrow(this.columns[colIndexTable]);//indice de la columna de la tabla para consultar en la parte sql
			if(cursor.isNull(colIndexSql)){
				resultado[it] = null;
			
			//La parte comentada de debajo corresponde al inentento en que:
			//Si la columna es textual o es ambigua y viene de tipo string, leeríamos el string
		    //la pena es que esto no funciona en versiones previas, 
			//quiza si hago getInt y devueva 0, o getStr y devuelva '' sea una buena señal...¿?
			}else if(textual[colIndexTable] /*|| 
					this.indexAmbiguo == it && android.os.Build.VERSION.SDK_INT >= 11 
						&& cursor.getType(it)==Cursor.FIELD_TYPE_STRING */){
				
				resultado[it]=cursor.getString(colIndexSql);
			}else{
				resultado[it]=Integer.valueOf(cursor.getInt(colIndexSql));
			}
		}
		return resultado;
	}
}
