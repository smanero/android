/**
 * 
 */
package org.stt.db.transformer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.stt.db.LoadedBean;
import org.stt.model.history.Option;
import org.stt.model.history.StReference;
import org.stt.model.history.StText;
import org.stt.service.TextService;

/**
 * Case para tranformar objetos LoadedBean en objetos del modelo
 * @author Gaizka
 *
 */
public class OptionTransformer {
	
	/**
	 * Constructor
	 */
	private OptionTransformer(){
	}

	/**
	 * Convierte de una clase a otra
	 * @param lb
	 * @return
	 */
	public static Option transform(LoadedBean lb, Map<Object, StReference> referenceMap, TextService textService){
		// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
		final Option op = new Option();
		final StText texto = textService.getText(lb.values[0]);
		op.setTexto(texto);
		op.setNextPageId(referenceMap.get(lb.values[1]));
		
		return op;
	}
	/**
	 * Transforma el mapa entero usando mapTransform.
	 * @param loadedBeanMap
	 * @return
	 */
	public static Map<Object, Option> transformMap(Map<Object, LoadedBean> loadedBeanMap, Map<Object, StReference> referenceMap, TextService textService){
		if(loadedBeanMap == null || loadedBeanMap.isEmpty()){
			return Collections.emptyMap();
		}
		final Map<Object, Option> result = new HashMap<Object, Option>(loadedBeanMap.size());
		for(Entry<Object, LoadedBean> elb: loadedBeanMap.entrySet()){
			result.put(elb.getKey(), transform(elb.getValue(), referenceMap, textService));
		}
		return result;
	}
}
