/**
 * 
 */
package org.stt.model.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un texto a mostrar, sea en opciones, en la página u otro lado.
 * Se distingue radicalmetne de los String en que un StText puede contener texto dinámico
 * El texto dinámico será gestionado por el StPropertyManager
 * @author Gaizka
 *
 */
public class StText {
	private List<Object> subtextos =  Collections.emptyList();

	/**
	 * Constructor
	 * @param subtextos Strings estáticos y referencias dinámicas en orden de concatenación
	 */
	public StText(List<Object> subtextos){
		this.subtextos = subtextos;
	}
	
	/**
	 * Constructor
	 * @param texto String
	 */
	public StText(String texto){
		this.subtextos = new ArrayList<Object>(2);
		this.subtextos.add(texto);
	}
	
	/**
	 * Compone y devuelve el texto
	 * @return
	 */
	public String getText(){
		if(this.subtextos.size() == 1){
			return this.subtextos.get(0).toString();
		}else if(this.subtextos.size() == 0){
			return "";
		}else{
			final Iterator<Object> it = this.subtextos.iterator();
			final List<String> slist = new ArrayList<String>(this.subtextos.size());
			final Iterator<String> it2;
			final StringBuilder sb;
			int szsb = 0;
			Object aux = null;
			String str = null;
			while(it.hasNext()){
				aux = it.next();
				str = StText.getString(aux);
				szsb += str.length();
				slist.add(str);
			}
			sb = new StringBuilder(szsb);
			it2 = slist.iterator();
			while(it2.hasNext()){
				sb.append(it2.next());
			}
			return sb.toString();
		}
	}
	
	/**
	 * para ayudar a liberar memoria
	 */
	public void clear(){
		if(!this.subtextos.isEmpty()){
			this.subtextos.clear();
		}
	}

	/**
	 * Saca el string que tienes dentro...
	 * @param aux puede ser un parameter (PageId parameter)
	 * @return
	 */
	public static final String getString(Object aux){
		final String str;
		if(aux instanceof StReference){
			str = StPropertyManager.getInstance().read((StReference) aux).toString();
		}else{
			str = aux.toString();
		}
		return str;
	}
}
