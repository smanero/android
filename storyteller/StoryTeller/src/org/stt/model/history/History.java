package org.stt.model.history;



/**
 * Probablemente en el futuro esta clase requiera de acceso a datos.
 * O bien crear los hastable antes de empezar, como en esta implementación.
 * @author BIHEALGA
 *
 */
public abstract class History {
	public static final StReference INIT_PAGE_ID = new StReference("init", StReference.ReferenceType.PAGE);
	protected static final int MAX_ITERATIONS = 20;
	String name;
	
	/**
	 * @param pageId Id de una Page
	 * @return Page que corresponda directamente a esa pageId
	 */
	protected abstract Page getPage(StReference pageId);
	/**
	 * 
	 * @param pageDispacherId Id de una PageDispacher
	 * @return PageDispacher que corresponda directamente a esa pageId
	 */
	protected abstract PageDispacher getPageDispacher(StReference pageDispacherId);
	/**
	 * Obtiene la Page a mostrar descendiente del pageId dado.
	 * Si el pageId no corresponde a un Page que se pueda mostrar
	 * sigue el grafo hasta encontrar la Page que se pueda mostrar.
	 * @param pageId Inicio del subgrafo a seguir por la historia.
	 * @return Next Page to show in the history.
	 * @throws Exception When too many iterations between dispachers.
	 * 	It's a fail of the logic in the history, as a infinite loop.
	 */
	public Page getFollowPage(StReference pageId) throws Exception{
		int loopCounter = 0;
		StReference currRed = pageId;
		PageDispacher dispacher = null;
		Comparable<?> comp;
		while(!pageId.isPage()){
			if(pageId.isDispacher()){
				dispacher = this.getPageDispacher(currRed);
				currRed = dispacher.nextPage(Game.getInstance().propiedades);
			}else{ //pageId is parameter
				comp = StPropertyManager.getInstance().read(pageId);
				//TODO distinguir entre dispachers y pages u otros objetos aqui
				currRed = new StReference(comp.toString(), StReference.ReferenceType.PAGE);
			}
			if(loopCounter > MAX_ITERATIONS){
				throw new Exception("too many iterations between dispachers");
			}
		}
		
		return this.getPage(currRed);
	}
}
