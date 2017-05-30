/**
 * 
 */
package org.stt.db.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stt.db.LoadedBean;
import org.stt.model.history.StReference;

import android.util.Log;

/**
 * Case para tranformar objetos LoadedBean en objetos del modelo
 * @author Gaizka
 *
 */
public class SubtextTransformer {

	/**
	 * Convierte el LoadedBean en un subtexto.
	 * Para ello necesita tener las referencias mapeadas, por si se usaran.
	 * @param lb objeto con datos a interpretar
	 * @param referenceMap fuente de relación con las referencias
	 * @return subtexto
	 */
	public static Object transform(LoadedBean lb, Map<Object, StReference> referenceMap){
		// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
		final Integer esReferencia = (Integer) lb.values[1];
		
		if(esReferencia == null || esReferencia.intValue() == 0){
			return lb.values[3];//el texto str
		}else{
			if(referenceMap == null){
				final String msg= "referenceMap==null, probablemente haya que definirlo en el TextService";
				Log.e(SubtextTransformer.class.getSimpleName(), msg);
				throw new RuntimeException(msg);
			}
			return referenceMap.get(lb.values[2]);//la referencia
		}
	}
	
	/**
	 * Obtiene la lista de subtextos
	 * @param desmembrado lista ordenada
	 * @return lista de subtextos para texto dinámico
	 */
	public static List<Object> transformSet(List<LoadedBean> desmembrado, Map<Object, StReference> referenceMap){
		final ArrayList<Object> subtextos = new ArrayList<Object>(desmembrado.size()+1);
		for(LoadedBean lb: desmembrado){
			subtextos.add(transform(lb, referenceMap));
		}
		return subtextos;
	}
}
