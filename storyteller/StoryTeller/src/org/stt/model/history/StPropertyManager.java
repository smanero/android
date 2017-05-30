/**
 * 
 */
package org.stt.model.history;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import android.annotation.SuppressLint;

/**
 * @author Gaizka
 *
 */
public class StPropertyManager {
	private static StPropertyManager propertyManager;
	
	private Hashtable<String, Comparable<?>> prop = new Hashtable<String, Comparable<?>>();

	/**
	 * 
	 */
	private StPropertyManager() {
	}

	public static final StPropertyManager getInstance(){
		if(propertyManager == null){
			propertyManager = new StPropertyManager();
		}
		return propertyManager;
	}
	/**
	 * para ayudar a liberar memoria
	 */
	public static final void clear(){
		if(propertyManager != null){
			propertyManager.prop.clear();
			propertyManager.prop = null;
			propertyManager = null;
		}
	}
	
	public Comparable<?> read(StReference ref){
		final Comparable<?> aux;
		switch(ref.getType()){
		case CONSTANT:
			aux = ref.getKey();
			break;
		case CONSTANT_INT:
			aux = Integer.valueOf(ref.getKey());
			break;
		case VARIABLE:
		case VARIABLE_INT:
			aux = prop.get(ref.getKey());
			break;
		case GAME_PROPERTY:
			aux = Game.getInstance().propiedades.getProperty(ref.getKey());
			break;
		case GAME_INT_PROPERTY:
			aux = Game.getInstance().propiedades.getProperty(ref.getKey());//TODO Implementer tipo Integer
			break;
		case CAL_PROPERTY:
			aux = this.readTime(ref.getKey());
			break;
		case SDF_PROPERTY:
			aux = this.readSDF(ref.getKey());
			break;
		default:
			throw new UnsupportedOperationException("Referencia inválida " + ref.getKey());
		}
		if(aux == null){
			throw new UnsupportedOperationException("Propiedad inexistente, key : " + ref.getKey());
		}
		return aux;
	}
	
	/**
	 * Obtencion de parametros llamados de sistema
	 * @param key nombre del parametro
	 * @return valor
	 */
	private Comparable<?> readTime(String key) {
		int cal = 0;
		Field field = null;
		try {
			field = Calendar.class.getField(key);
			cal = field.getInt(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return Integer.valueOf(Calendar.getInstance().get(cal));
	}
	/**
	 * Obtencion de parametros llamados de sistema
	 * @param key nombre del parametro
	 * @return valor
	 */
	@SuppressLint("SimpleDateFormat")
	private Comparable<?> readSDF(String key) {
		final SimpleDateFormat sdf;
		sdf = new SimpleDateFormat(key);
		return sdf.format(sdf.getCalendar().getTime());
	}

	/**
	 * Escribe la propiedad señalada existiera o no antes
	 * @param val
	 */
	public void write(String key, Comparable<?> val){
		prop.put(key, val);
	}

	/**
	 * Si no estába definido lo define con los valores dados.
	 * Si ya exisitía no cambia nada
	 * @param key key
	 * @param value valor
	 */
	public void define(String key, Comparable<?> value){
		if(!prop.containsKey(key)){
			prop.put(key, value);
		}
	}
	
	public boolean exists(StReference ref){
		return ref != null
				&& prop.containsKey(ref.getKey());
	}
}
