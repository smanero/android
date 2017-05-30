package org.stt.db.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.stt.db.LoadedBean;
import org.stt.model.history.Page;
import org.stt.model.history.StReference;

/**
 * Case para tranformar objetos LoadedBean en objetos del modelo
 * @author Gaizka
 *
 */
public class ReferenceTrasnformer {

	/**
	 * Constructor
	 */
	private ReferenceTrasnformer(){
	}
	/**
	 * 
	 * @param loadedMap datos en crudo
	 * @return mapa de referencias
	 */
	public static Map<Object, StReference> transformerMap(Map<Object, LoadedBean> loadedMap){
		// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
		final Map<Object, StReference> retorno = new HashMap<Object, StReference>(loadedMap.size());
		//mapa para conversión de tipos de dato
		final int szmptp = 16;
		final HashMap<String, StReference.ReferenceType> translatorType = new HashMap<String, StReference.ReferenceType>(szmptp);

		//mapa para traducir lo que viene de bbdd en cuanto al tipo de referencia
		translatorType.put(StReference.ReferenceType.PAGE.shortStr, StReference.ReferenceType.PAGE);
		translatorType.put(StReference.ReferenceType.DISPACHER.shortStr, StReference.ReferenceType.DISPACHER);
		translatorType.put(StReference.ReferenceType.VARIABLE.shortStr, StReference.ReferenceType.VARIABLE);
		translatorType.put(StReference.ReferenceType.VARIABLE_INT.shortStr, StReference.ReferenceType.VARIABLE_INT);
		translatorType.put(StReference.ReferenceType.GAME_PROPERTY.shortStr, StReference.ReferenceType.GAME_PROPERTY);
		translatorType.put(StReference.ReferenceType.CAL_PROPERTY.shortStr, StReference.ReferenceType.CAL_PROPERTY);
		translatorType.put(StReference.ReferenceType.SDF_PROPERTY.shortStr, StReference.ReferenceType.SDF_PROPERTY);
		translatorType.put(StReference.ReferenceType.CONSTANT.shortStr, StReference.ReferenceType.CONSTANT);
		translatorType.put(StReference.ReferenceType.CONSTANT_INT.shortStr, StReference.ReferenceType.CONSTANT_INT);
		
		StReference referencia = null;
		LoadedBean lb = null;
		for(Entry<Object, LoadedBean> elb : loadedMap.entrySet()){
			lb = elb.getValue();
			// OJO!! los values corresponden a un index un punto por debajo del declarado en ItemTable.columns
			referencia = new StReference((String) lb.values[1], translatorType.get(lb.values[0]));
			retorno.put(elb.getKey(), referencia);
		}
		
		translatorType.clear();
		
		return (Map<Object, StReference>) retorno;
	}

	/**
	 * Extrae de las páginas su referencia y se la asigna
	 * @param pageMap mapa de páginas
	 * @return mapa de referencias de página
	 */
	public static Map<Object, StReference> popRef(Map<Object, Page> pageMap){
		final Map<Object, StReference> retorno = new HashMap<Object, StReference>(pageMap.size());
		
		StReference ref = null;
		for(Entry<Object, Page> ep: pageMap.entrySet()){
			ref = new StReference((String) ep.getKey(), StReference.ReferenceType.PAGE);
			ep.getValue().setId(ref);
			retorno.put(ref.getKey(), ref);
		}
		
		return retorno;
	}
}
