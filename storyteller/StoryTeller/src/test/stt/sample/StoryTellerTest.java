package test.stt.sample;

import java.util.ArrayList;
import java.util.Hashtable;

import org.stt.model.history.FullLoadedHistory;
import org.stt.model.history.History;
import org.stt.model.history.Option;
import org.stt.model.history.Page;
import org.stt.model.history.StAction;
import org.stt.model.history.StOperation;
import org.stt.model.history.StOperation.OpTp;
import org.stt.model.history.StPropertyManager;
import org.stt.model.history.StReference;
import org.stt.model.history.StText;

/**
 * @author BIHEALGA
 */
public class StoryTellerTest {
	
    /**
     * @deprecated
     * Pequeña historia para pruebas
     * @return Mock Data History
     */
    public static History makeASimpleHistory(){
    	final FullLoadedHistory historia = new FullLoadedHistory(3, 0);
    	final Option optionAux1, optionAux2, optionAux3;
    	final Page pageAux1, pageAux2, pageAux3;
    	final StReference page1, page2, page3;
    	final StReference val1, cte1, minutes;
    	final StText texto1;
    	
    	//ids de páginas
    	page1 = History.INIT_PAGE_ID;
    	page2 = new StReference("page2", StReference.ReferenceType.PAGE);
    	page3 = new StReference("page3", StReference.ReferenceType.PAGE);

    	// variables
    	val1 = new StReference("val1", StReference.ReferenceType.VARIABLE_INT);
    	minutes = new StReference("MINUTE", StReference.ReferenceType.CAL_PROPERTY);
    	cte1 = new StReference("1", StReference.ReferenceType.CONSTANT_INT);
    	StPropertyManager.getInstance().define("val1", Integer.valueOf(0));
    	
    	//operaciones y actions
    		//quiero sumar 1 cada vez que se ejecuta la accion
    	final StOperation operation = new StOperation(OpTp.APPEND, val1, cte1);
    	final ArrayList<StOperation> operations1 = new ArrayList<StOperation>(1);
    	operations1.add(operation);
    	final StAction action1 = new StAction();
    	action1.setOperations(operations1);
    	
    	//opciones (que reutilizo en varias páginas)
    	optionAux1 = new Option();
    	optionAux1.setDescription("pasar a la pagina 1");
    	optionAux1.setNextPageId(page1);
    	optionAux2 = new Option();
    	optionAux2.setDescription("pasar a la pagina 2");
    	optionAux2.setNextPageId(page2);
    	optionAux3 = new Option();
    	optionAux3.setDescription("pasar a la pagina 3");
    	optionAux3.setNextPageId(page3);
    	optionAux3.setActions(new ArrayList<StAction>());
    	optionAux3.getActions().add(action1);// sumar 1 a val1 cada vez que llegamos al final
    	
    	//textos compuestos
    	ArrayList<Object> subtextos = new ArrayList<Object>(8);
    	subtextos.add("Esto es un ejemplo cortísimo de historia. Tan sólo de dos o tres páginas.\n(y aquí va la primera de hecho)\n Minutos: ");
    	subtextos.add(minutes);
    	subtextos.add("\n veces terminado: ");
    	subtextos.add(val1);
    	subtextos.add("\n cte: ");
    	subtextos.add(cte1);
    	texto1 = new StText(subtextos);
    	
    	//Páginas
    	pageAux1 = new Page();
    	pageAux1.setId(page1);
    	pageAux1.setTexto(texto1);
    	//pageAux1.setTextHistory("Esto es un ejemplo cortísimo de historia. Tan sólo de dos o tres páginas.\n(y aquí va la primera de hecho)");
    	pageAux1.setOptionList(new ArrayList<Option>(2));
    	pageAux1.getOptionList().add(optionAux2);
    	pageAux1.getOptionList().add(optionAux3);
    	
    	pageAux2 = new Page();
    	pageAux2.setId(page2);
    	pageAux2.setTextHistory("Estás ahora en el intermedio de la historia.\nLa llegada al final es inminiente, porque no queda más opciones.\n:P");
    	pageAux2.setOptionList(new ArrayList<Option>(1));
    	pageAux2.getOptionList().add(optionAux3);
    	
    	pageAux3 = new Page();
    	pageAux3.setId(page3);
    	pageAux3.setTextHistory("Esto es el final de la historia, pero daremos la opción a volver a los puntos anteriores, por si se desea revivir la aventura.");
    	pageAux3.setOptionList(new ArrayList<Option>(2));
    	pageAux3.getOptionList().add(optionAux1);
    	pageAux3.getOptionList().add(optionAux2);
    	
    	//historia
    	historia.setPageMap(new Hashtable<StReference, Page>(3)); 
    	historia.putPage(pageAux1);
    	historia.putPage(pageAux2);
    	historia.putPage(pageAux3);
    	
    	
    	return historia;
    }  
}
