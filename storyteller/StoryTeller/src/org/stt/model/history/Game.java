package org.stt.model.history;

import java.util.Properties;

import android.util.Log;

/**
 * it's pretend to be a representation of current game opened
 * 
 * @author BIHEALGA
 * 
 */
public class Game {

	History historia;
	Properties propiedades;
	protected StReference currentPageId;
	protected Page currentPageBean;
	protected Page defaultPage;

	protected static Game currentGame;

	/**
	 * constructor privado para simular singelton
	 */
	protected Game() {
		super();
	}

	/**
	 * @return current game
	 */
	public static Game getInstance() {
		if (Game.currentGame == null) {
			Game.currentGame = new Game();
		}
		return Game.currentGame;
	}

	/**
	 * Usar para iniciar el Game con una historia o para reemplazarla con otra.
	 * 
	 * @param historia
	 *            (requerido) History para obtener de �l las p�ginas que tocan
	 *            en cada momento
	 * @param propiedades
	 *            (opcional) Datos de partida
	 * @param currentPageId
	 *            (opcional) Default, History.INIT_PAGE_ID
	 * @return
	 */
	public static Game init(History historia, Properties propiedades,
			StReference currentPageId) {
		assert historia != null;
		final Game game = Game.getInstance();
		game.historia = historia;
		game.propiedades = propiedades;
		if (currentPageId == null) {
			game.currentPageId = History.INIT_PAGE_ID;//default page y p�gina inicial de todas las historias
		} else {
			game.currentPageId = currentPageId;
		}
		if(game.propiedades == null){
			game.propiedades = new Properties();
		}
		return game;
	}

	/**
	 * Obtiene la p�gina en curso. Para ello mira primero si ya la tiene
	 * "cacheada". Si no la tiene usa "historia" para obtenerla.
	 * Ejecuta las acciones de la pagina si la obtiene desde la historia.
	 * 
	 * @return
	 */
	public Page getCurrentPage() {
		if (this.currentPageBean == null
				|| !this.currentPageBean.getId().equals(this.currentPageId)) {
			if (this.historia == null) {
				this.currentPageBean = this.defaultPage;
			} else {
				if(this.currentPageId == null){
					Log.e(this.getClass().getSimpleName(), "No hay currentPageId. usamos usamos p�gina default de la historia");
					this.currentPageId = History.INIT_PAGE_ID;
				}
				this.currentPageBean = this.historia
						.getPage(this.currentPageId);
				//lanza una vez por cada entrada desde la historia
				if(this.currentPageBean == null){
					String msg = "La clave de la p�gina no se encuentra en la historia. La clave fallida es: " + this.currentPageId.getKey();
					if(this.currentPageId.equals(History.INIT_PAGE_ID)){
						msg += ". \nToda historia debe comenzar por la p�gina de clave "+History.INIT_PAGE_ID.getKey();
					}
					Log.e(this.getClass().getSimpleName(), msg);
					this.currentPageBean = this.createWarnPage(msg);
					this.currentPageId = this.currentPageBean.getId();//por si se pierde,...
				}
				this.currentPageBean.shutActions();
			}
		}
		return this.currentPageBean;
	}
	
	/**
	 * Creamos una p�gina para mostrar alg�n mensaje al usuario de que las cosas no van bien, el lugar de dar un casque
	 * @param msg mensaje a mostrar
	 * @return p�gina a usar como actual
	 */
	private Page createWarnPage(String msg){
		final StText text = new StText(msg);
		final Page page = new Page();
		page.setId(History.INIT_PAGE_ID);//de momento con la idea de que al menos haya una p�gina init
		page.setTexto(text);
		
		return page;
	}

	/**
	 * Indica la selecci�n de otro page en el juego. Usar tras seleccionar una
	 * opci�n para cambiar la page
	 * 
	 * @param pageId
	 */
	public void choose(Option optionChoosed) {
		optionChoosed.shutActions();
		this.currentPageId = optionChoosed.followPageId();
	}
}
