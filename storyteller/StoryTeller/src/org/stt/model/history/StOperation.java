/**
 * 
 */
package org.stt.model.history;


/**
 * Representa una operacion básica de manipulacion de variables de juego.
 * En todos los casos la primera variable se redefine
 * @author Gaizka
 *
 */
public class StOperation {
	private final OpTp op;
	private final StReference arg1;
	private final StReference arg2;
	
	public static enum OpTp{
		APPEND (2), // a += b
		REMOVE (2), // a -=b
		SET (2);    // a = b
		
		protected int nArgs;
		private OpTp(int nArgs){
			this.nArgs = nArgs;
		}
	}

	/**
	 * Constructor para operaciones de dos argumentos
	 * @param op
	 */
	public StOperation(OpTp op, StReference arg1){
		if(this.isNotEmpty(arg1)){
			throw new RuntimeException("Argumentos de operación inválidos");
		}else if(!arg1.getType().w){
			throw new RuntimeException("El primer argumento debe ser editable, arg: " + arg1.getKey());
		}else if(op.nArgs != 1){
			throw new RuntimeException("Numero de argumentos de operacion incorrecto");
		}
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = null;
	}
	/**
	 * Constructor para operaciones de dos argumentos
	 * @param op
	 */
	public StOperation(OpTp op, StReference arg1, StReference arg2){
		if(this.isNotEmpty(arg1) 
				|| this.isNotEmpty(arg2)){
			throw new RuntimeException("Argumentos de operación nulos");
		}else if(!arg1.getType().w){
			throw new RuntimeException("El primer argumento debe ser editable, arg: " + arg1.getKey());
		}else if(op.nArgs != 2){
			throw new RuntimeException("Numero de argumentos de operacion incorrecto, op: " + op);
		}
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	private final boolean isNotEmpty(StReference arg){
		return arg == null || arg.getKey() == null || arg.getKey().length() == 0;
	}
	private int getInt(Comparable<?> val){
		if(val == null){
			return 0;
		}else if(val instanceof Integer){
			return ((Integer) val).intValue();
		}else{
			return Integer.parseInt(val.toString());
		}
	}

	public void shut(){
		final StPropertyManager pm = StPropertyManager.getInstance();
		Comparable<?> val1 = null, val2 = null, result = null;

		switch (op) {
		case APPEND:
			val1 = pm.read(arg1);
			val2 = pm.read(arg2);
			result = Integer.valueOf(this.getInt(val1) + this.getInt(val2));
			break;
		case REMOVE:
			val1 = pm.read(arg1);
			val2 = pm.read(arg2);
			result = Integer.valueOf(this.getInt(val1) - this.getInt(val2));
			break;
		case SET:
			val2 = pm.read(arg2);
			result = val2;
		}
		pm.write(arg1.getKey(), result);
	}
}
