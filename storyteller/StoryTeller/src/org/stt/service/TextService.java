/**
 * 
 */
package org.stt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stt.db.LoadedBean;
import org.stt.db.SttDbAdapter;
import org.stt.db.table.TextsTable;
import org.stt.db.transformer.SubtextTransformer;
import org.stt.model.history.StReference;
import org.stt.model.history.StText;

import android.util.Log;

/**
 * Clase que devuelve Textos, sean ya precargados o sean por demanda de la bbdd
 * @author Gaizka
 *
 */
public class TextService {
	private SttDbAdapter dbAdapter;
	private final Map<Object, StText> textMap = new HashMap<Object, StText>();
	private Map<Object, StReference> referenceMap;
	private TextsTable table;
	
	public TextService(SttDbAdapter dbAdapter){
		this.dbAdapter = dbAdapter;
	}

	public void setReferenceMap(Map<Object, StReference> referenceMap){
		this.referenceMap = referenceMap;
	}
	public Map<Object, StReference> getReferenceMap(){
		return referenceMap;
	}
	
	private TextsTable getTextTable(){
		if(table==null){
			table= new TextsTable();
		}
		return table;
	}
	
	/**
	 * Busca el texto en el mapa y si no lo tiene lo busca en la bbdd y lo registra
	 * @param textoId
	 * @return texto
	 */
	public StText getText(Object textoId){
		Log.v(TextService.class.getSimpleName(), "getText");
		
		StText texto;
		texto = this.textMap.get(textoId);
		if(texto == null){
			final List<LoadedBean> desmembrado = this.dbAdapter.getRelatedRecords(this.getTextTable(), textoId);
			final List<Object> subtextos = SubtextTransformer.transformSet(desmembrado, this.referenceMap);
			texto = new StText(subtextos);
			this.textMap.put(textoId, texto);
		}
		return texto;
	}
	/**
	 * Elmina un texto para liberar memoria
	 * @param textoId
	 */
	public void removeText(Integer textoId){
		this.textMap.remove(textoId);
	}
	/**
	 * Para liberar memoria del textMap
	 */
	public void clearTextMap(){
		this.textMap.clear();
	}
	
	/**
	 * Cierra ayuda a liberar la memoria de la instancia
	 */
	public void clearAll(){
		this.clearTextMap();
		if(referenceMap!=null){
			referenceMap.clear();
			referenceMap = null;
		}
		table = null;
	}
}
