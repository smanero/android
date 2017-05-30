/**
 * 
 */
package org.stt.model.history;

import java.util.Collection;


/**
 * Representa una prueba lógica
 * @author Gaizka
 *
 */
public class Condition {
	private StReference property1; //obligatoria
	private StReference property2; //opcional segun tipo de relacion
	private RelationTp relationTp;
	
	private static Comparable<?> getProperty(StReference name){
		if(name == null){
			return null;
		}
		return StPropertyManager.getInstance().read(name);
	}
	/**
	 * CHEQUEA SI SE CUMPLE UNA CONDICIÓN.
	 * 
	 * @return TRUE SI SE CUMPLE
	 */
	@SuppressWarnings("unchecked")
	public boolean match(){
		//Excepciones al tratamiento sistemático posterior
		//si es condicion de existencia, es variable pero no está mapeada aún
		if(this.relationTp != null 
		&& this.relationTp.equals(RelationTp.EX)
		&& this.property1 != null
		&& this.property1.getType().w
		&& !StPropertyManager.getInstance().exists(this.property1)){
			return false;
		}
		//si es condicion de no existencia, es variable y no está mapeada aún
		else if(this.relationTp != null 
		&& this.relationTp.equals(RelationTp.NEX)
		&& this.property1 != null
		&& this.property1.getType().w
		&& !StPropertyManager.getInstance().exists(this.property1)){
			return true;
		}
		
		//Tratamiento sistemático, leer las variables y compararlas
		@SuppressWarnings("rawtypes")
		final Comparable p1, p2;
		p1 = getProperty(this.property1);
		p2 = getProperty(this.property2);
		
		//TODO: las comparaciones menor que y tal deberían chequear tipo de dato
		switch (this.relationTp) {
		case EQ:
			return p2 != null && p2.equals(p1);
		case NEQ:
			return p2 == null || !p2.equals(p1);
		case LT:
			return p2 != null && p1 != null && p2.compareTo(p1) > 0;
		case GT:
			return p2 != null && p1 != null && p2.compareTo(p1) < 0;
		case LT_EQ:
			return p2 != null && p1 != null && p2.compareTo(p1) >= 0;
		case GT_EQ:
			return p2 != null && p1 != null && p2.compareTo(p1) <= 0;
		//estas son de un parametro
		case EX:
			return p1 != null && p1.toString().length()>0;
		case NEX:
			return p1 == null || p1.toString().length()==0;
		default: return false;
		}
	}
	
	/**
	 * @return the propertyKey1
	 */
	public StReference getProperty1() {
		return property1;
	}
	/**
	 * @param propertyKey1 the propertyKey1 to set
	 */
	public void setProperty1(StReference property1) {
		this.property1 = property1;
	}
	/**
	 * @return the propertyKey2
	 */
	public StReference getProperty2() {
		return property2;
	}
	/**
	 * @param propertyKey2 the propertyKey2 to set
	 */
	public void setProperty2(StReference property2) {
		this.property2 = property2;
	}
	/**
	 * @return the relationTp
	 */
	public RelationTp getRelationTp() {
		return relationTp;
	}
	/**
	 * @param relationTp the relationTp to set
	 */
	public void setRelationTp(RelationTp relationTp) {
		this.relationTp = relationTp;
	}
	
	public static final boolean and(Collection<Condition> conditions){
		for(Condition condicion : conditions){
			if(!condicion.match()){
				return false;
			}
		}
		return true;
	}
	
	public static final boolean or(Collection<Condition> conditions){
		for(Condition condicion : conditions){
			if(condicion.match()){
				return true;
			}
		}
		return false;
	}

/**
 * Tipo de relacion
 * @author Gaizka
 *
 */
	public static enum RelationTp {
		EQ,
		NEQ, //not equal
		LT,
		GT,
		LT_EQ,
		GT_EQ,
		EX, // EXISTS
		NEX // NOT EXISTS
	}
	
}
