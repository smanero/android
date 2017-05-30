/**
 * 
 */
package org.stt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.stt.db.LoadedBean;
import org.stt.db.SttDbAdapter;
import org.stt.db.table.OptionsPageTable;
import org.stt.db.table.OptionsTable;
import org.stt.db.table.PagesTable;
import org.stt.db.table.ReferencesTable;
import org.stt.db.transformer.OptionTransformer;
import org.stt.db.transformer.PageTransformer;
import org.stt.db.transformer.ReferenceTrasnformer;
import org.stt.model.history.FullLoadedHistory;
import org.stt.model.history.History;
import org.stt.model.history.Option;
import org.stt.model.history.Page;
import org.stt.model.history.StReference;

import android.util.Log;

/**
 * Clase capaz de leer una historia al completo cargandola en memoria
 * @author Gaizka
 *
 */
public class HistoryLoaderService {
	final SttDbAdapter dbAdapter;
	
	public HistoryLoaderService(SttDbAdapter dbAdapter) {
		super();
		this.dbAdapter = dbAdapter;
	}
	
	/**
	 * Carga la history completa en memoria.
	 * Necesario haber iniciado el textService el cual ya no necesitará al terminar.
	 * La historia no se coge por parámetro ya que viene en ItemsTable definida.
	 * @return una fullHistory
	 */
	public History fullLoadHistory(){
		Log.v(this.getClass().getSimpleName(), "loadHistory");
		
		final Map<Object, StReference> referencesMap;
		Map<Object, StReference> referencesAuxMap;
		final Map<Object, Page> pagesMap;
		final Map<Object, Option> optionsMap;
		final TextService textService = new TextService(this.dbAdapter);

		//referencias de bbdd
		referencesAuxMap = loadFullReferencesDB();
		//Asignamos las referencias al TextService para que nos responda correctamente al hacer getText
		textService.setReferenceMap(referencesAuxMap);
		
		pagesMap = loadFullPageUnbind(textService);
		//TODO dispachers aqui
		
		//complemento las referencias con las páginas y dispachers
		final int szrefmap = referencesAuxMap.size() + pagesMap.size();
		
		referencesMap = new HashMap<Object, StReference>(szrefmap);
		referencesMap.putAll(referencesAuxMap);
		//referencesAuxMap.clear();//NO LIMPIAR, PORQUE ESTE MAPA ESTA AHORA EN EL TEXT SERVICE
		
		referencesAuxMap = ReferenceTrasnformer.popRef(pagesMap);
		referencesMap.putAll(referencesAuxMap);
		referencesAuxMap.clear();
		//TODO Idem con page dispachers
		
		optionsMap = loadFullOptionUnbind(referencesMap, textService);
		
		//fin de la parte que implica textos...
		textService.clearAll();

		//historia resultado para ir metiendole páginas
		final FullLoadedHistory his = new FullLoadedHistory(pagesMap.size(), 0);
		
		//VINCULAR ENTRE SI
		//Opciones a páginas, y páginas a la historia en la misma iteración
		for(Entry<Object, Page> ePage: pagesMap.entrySet()){
			this.bindPageToOptions(optionsMap, ePage.getValue());
			his.putPage(ePage.getValue());
		}
		
		return his;
	}
	
	/**
	 * Consigue todas las opciones pero sin vincular a ellas los objetos tipo lista, como acciones y condiciones
	 * @param textsMap mapa de textos completo.
	 * @param referencesMap mapa de referencias completo.
	 * @return mapa de objetos completo
	 */
	public Map<Object, Option> loadFullOptionUnbind(Map<Object, StReference> referencesMap, TextService textService){
		Log.v(this.getClass().getSimpleName(), "loadFullOptionUnbind");
		
		final HashMap<Object, LoadedBean> optionsLBM;
		final Map<Object, Option> optionsMap;
		final OptionsTable optTable = new OptionsTable();
		optionsLBM = dbAdapter.getAllRecords(optTable);
		optionsMap = OptionTransformer.transformMap(optionsLBM, referencesMap, textService);
		optionsLBM.clear();
		
		return optionsMap;
	}
	/**
	 * Carga todas las referencias de la bbdd
	 * @return referencias de la bbdd
	 */
	public Map<Object, StReference> loadFullReferencesDB(){
		Log.v(this.getClass().getSimpleName(), "loadFullReferencesDB");
		
		final HashMap<Object, LoadedBean> referencesLBM;
		final Map<Object, StReference> referencesMap;
		final ReferencesTable refTable = new ReferencesTable();

		referencesLBM = dbAdapter.getAllRecords(refTable);
		referencesMap = ReferenceTrasnformer.transformerMap(referencesLBM);
		referencesLBM.clear();
		
		return referencesMap;
	}
	/**
	 * Lee todas las páginas sin vincularle a ellas objetos del modelo
	 * @return
	 */
	public Map<Object, Page> loadFullPageUnbind(TextService textService){
		Log.v(this.getClass().getSimpleName(), "loadFullPageUnbind");
		
		final Map<Object, Page> pagesMap;
		final HashMap<Object, LoadedBean> pagesLBM;
		final PagesTable pageTable = new PagesTable();

		pagesLBM = dbAdapter.getAllRecords(pageTable);
		pagesMap = PageTransformer.mapTransform(pagesLBM, textService);
		pagesLBM.clear();
		
		return pagesMap;
	}
	/**
	 * Encuentra y vincula las opciones a la página
	 * @param optionsMap opciones previamente cargadas
	 * @param page pagina a vincular
	 */
	public void bindPageToOptions(Map<Object, Option> optionsMap, Page page){
		Log.v(this.getClass().getSimpleName(), "bindPageToOptions");
		
		final OptionsPageTable table = new OptionsPageTable();
		final List<LoadedBean> lbList;
		
		lbList = dbAdapter.getRelatedRecords(table, page.getId().getKey());
		
		Option option = null;
		final List<Option> optionList = new ArrayList<Option>(optionsMap.size()+1);//opciones de la pagina
		for(LoadedBean lb: lbList){
			option = optionsMap.get(lb.values[1]);//valor del campo opcion, Los indices son uno menor que en ItemsTable.columns
			optionList.add(option);
		}
		page.setOptionList(optionList);
	}
}