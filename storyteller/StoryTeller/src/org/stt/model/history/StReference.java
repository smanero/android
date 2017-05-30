package org.stt.model.history;


/**
 * Esta clase trata de una referencia a otros objetos.
 * Estos pueden ser por ejemplo: páginas, variables de juego, etc.
 * @author Gaizka
 *
 */
public class StReference {

	//el tipo de variable indica principalmente donde se almacena su información
	//también representa la funcion aunque en esto es más versatil
	// por ejemplo una variable puede almacenar una key de página válida
	public static enum ReferenceType {
		PAGE (false, false, "P"), //una id de página
		DISPACHER (false, false, "D"), //un id de dispacher de paginas
		VARIABLE (true, false, "V"), // un id de parametro gestionado directamente por el property manager
		VARIABLE_INT (true, true, "VI"), // un id de parametro gestionado directamente por el property manager, INTEGER
		GAME_PROPERTY (false, false, "G"), // una propiedad de game como la pagina anterior o la actual
		GAME_INT_PROPERTY (false, false, "GI"), // una propiedad de game como la pagina anterior o la actual Integer
		CAL_PROPERTY (false, true, "CAL"), // una propiedad de calendar como la hora o la fecha
		SDF_PROPERTY (false, false, "SDF"), // una propiedad de SimpleDate format
		CONSTANT (false, false, "C"),// la propia key es el valor deseado
		CONSTANT_INT (false, true, "CI")// la propia key es el valor deseado; integer
		;
		public final boolean w;//si es machacable
		public final boolean i;//si es entero
		public final String shortStr; //nombre corto para bbdd y serializacion
		private ReferenceType(boolean w, boolean i, String shortStr){
			this.w = w;
			this.i = i;
			this.shortStr = shortStr;
		}
	}
	
	private final String key;
	private final ReferenceType type;

	public ReferenceType getType() {
		return this.type;
	}

	public boolean isPage() {
		return ReferenceType.PAGE.equals(this.type);
	}

	public boolean isDispacher() {
		return ReferenceType.DISPACHER.equals(this.type);
	}

	public String getKey() {
		return this.key;
	}

	public StReference(String key, ReferenceType type) {
		this.key = key;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getKey().hashCode();
		result = 31 * result + this.getType().hashCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(o==null || !(o instanceof StReference )){
			return false;
		}
		final StReference b = (StReference) o;
		return this.getType().equals(b.getType()) && this.getKey().equals(b.getKey());
	}
}
