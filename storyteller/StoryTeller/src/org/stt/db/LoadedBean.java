/**
 * 
 */
package org.stt.db;

/**
 * Una expresión de Enty
 * Representa un elemento del modelo cargado de la bbdd tipo par key - valores
 * Donde la key servirá para organizar y localizar el objeto dentro de un Map o Set
 * y los valores servirán para construir finalmente el objeto o encontrar referencias.
 * Los valores se informan en el orden de las columnas, omitiendo la key y un indice por debajo.
 * OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
 * @author Gaizka
 *
 */
public class LoadedBean {
	public Object key;
	// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
	public Object[] values;
}
