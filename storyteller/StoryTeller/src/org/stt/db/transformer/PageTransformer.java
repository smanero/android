/**
 * 
 */
package org.stt.db.transformer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.stt.db.LoadedBean;
import org.stt.model.history.Page;
import org.stt.service.TextService;

/**
 * Case para tranformar objetos LoadedBean en objetos del modelo
 * @author Gaizka
 *
 */
public class PageTransformer {
	
	private PageTransformer() {
	}
	
	/**
	 * Convierte de una clase a otra.
	 * @param lb
	 * @return
	 */
	public static Page transform(LoadedBean lb, TextService textService){
		// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
		final Page page = new Page();
		
		page.setTexto(textService.getText(lb.values[0]));
		page.setLocation(lb.values[1]==null?"":lb.values[1].toString());
		
		return page;
	}

	/**
	 * Transforma el mapa entero usando mapTransform.
	 * @param loadedBeanMap
	 * @return
	 */
	public static Map<Object, Page> mapTransform(Map<Object, LoadedBean> loadedBeanMap, TextService textService){
		if(loadedBeanMap == null || loadedBeanMap.isEmpty()){
			return Collections.emptyMap();
		}
		final Map<Object, Page> result = new HashMap<Object, Page>(loadedBeanMap.size());
		for(Entry<Object, LoadedBean> elb: loadedBeanMap.entrySet()){
			result.put(elb.getKey(), transform(elb.getValue(), textService));
		}
		return result;
	}
	
}
