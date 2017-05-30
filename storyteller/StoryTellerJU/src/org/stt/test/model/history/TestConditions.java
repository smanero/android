/**
 * 
 */
package org.stt.test.model.history;

import java.util.ArrayList;
import java.util.List;

import org.stt.model.history.Condition;
import org.stt.model.history.Condition.RelationTp;
import org.stt.model.history.StReference;
import org.stt.model.history.StReference.ReferenceType;

import junit.framework.TestCase;

/**
 * @author Gaizka
 *
 */
public class TestConditions extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias textuales y condicion EQ
	 */
	public void testMatchStrEQ() {
		//referencias iguales o diferentes
		final StReference ref = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refDif = new StReference("cte2", ReferenceType.CONSTANT);
		
		//condiciones que se cumplen o no
		final Condition condMatch = new Condition();
		condMatch.setProperty1(ref);
		condMatch.setProperty2(refClon);
		condMatch.setRelationTp(RelationTp.EQ);
		final Condition condMatch2 = new Condition();
		condMatch2.setProperty1(ref);
		condMatch2.setProperty2(refDif);
		condMatch2.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch = new Condition();
		condNotMatch.setProperty1(ref);
		condNotMatch.setProperty2(refClon);
		condNotMatch.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch2 = new Condition();
		condNotMatch2.setProperty1(ref);
		condNotMatch2.setProperty2(refDif);
		condNotMatch2.setRelationTp(RelationTp.EQ);

		assertTrue(condMatch.match());
		assertTrue(condMatch2.match());
		assertFalse(condNotMatch.match());
		assertFalse(condNotMatch2.match());
	}
	
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias numéricas y condicion EQ y NEQ
	 */
	public void testMatchIntEQ() {
		//referencias iguales o diferentes
		final StReference ref = new StReference("10", ReferenceType.CONSTANT_INT);
		final StReference refClon = new StReference("10", ReferenceType.CONSTANT_INT);
		final StReference refDif = new StReference("5", ReferenceType.CONSTANT_INT);
		
		//condiciones que se cumplen o no
		final Condition condMatch = new Condition();
		condMatch.setProperty1(ref);
		condMatch.setProperty2(refClon);
		condMatch.setRelationTp(RelationTp.EQ);
		final Condition condMatch2 = new Condition();
		condMatch2.setProperty1(ref);
		condMatch2.setProperty2(refDif);
		condMatch2.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch = new Condition();
		condNotMatch.setProperty1(ref);
		condNotMatch.setProperty2(refClon);
		condNotMatch.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch2 = new Condition();
		condNotMatch2.setProperty1(ref);
		condNotMatch2.setProperty2(refDif);
		condNotMatch2.setRelationTp(RelationTp.EQ);

		assertTrue(condMatch.match());
		assertTrue(condMatch2.match());
		assertFalse(condNotMatch.match());
		assertFalse(condNotMatch2.match());
	}

	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias string y condicion GT mayor qué extríctamente
	 */
	public void testMatchStrGT() {
		//relación a comparar
		final RelationTp tp = RelationTp.GT;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refLess = new StReference("albo", ReferenceType.CONSTANT);
		final StReference refGreather = new StReference("alto", ReferenceType.CONSTANT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refGreather);
		cond4.setRelationTp(tp);

		//assets
		assertFalse(cond1.match());
		assertFalse(cond2.match());
		assertTrue(cond3.match());
		assertFalse(cond4.match());
	}
	
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias numéricas y condicion GT mayor qué extríctamente
	 */
	public void testMatchIntGT() {
		//relación a comparar
		final RelationTp tp = RelationTp.GT;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refClon = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refLess = new StReference("5", ReferenceType.CONSTANT_INT);
		final StReference refOposite = new StReference("-100", ReferenceType.CONSTANT_INT);
		final StReference refGreather = new StReference("159", ReferenceType.CONSTANT_INT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refOposite);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(ref);
		cond5.setProperty2(refGreather);
		cond5.setRelationTp(tp);

		//assets
		assertFalse(cond1.match());
		assertFalse(cond2.match());
		assertTrue(cond3.match());
		assertTrue(cond4.match());
		assertFalse(cond5.match());
	}

	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias string y condicion LT menor qué extríctamente
	 */
	public void testMatchStrLT() {
		//relación a comparar
		final RelationTp tp = RelationTp.LT;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refLess = new StReference("albo", ReferenceType.CONSTANT);
		final StReference refGreather = new StReference("alto", ReferenceType.CONSTANT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refGreather);
		cond4.setRelationTp(tp);

		//assets
		assertFalse(cond1.match());
		assertFalse(cond2.match());
		assertFalse(cond3.match());
		assertTrue(cond4.match());
	}
	
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias numéricas y condicion LT menor qué extríctamente
	 */
	public void testMatchIntLT() {
		//relación a comparar
		final RelationTp tp = RelationTp.LT;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refClon = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refLess = new StReference("5", ReferenceType.CONSTANT_INT);
		final StReference refOposite = new StReference("-100", ReferenceType.CONSTANT_INT);
		final StReference refGreather = new StReference("159", ReferenceType.CONSTANT_INT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refOposite);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(ref);
		cond5.setProperty2(refGreather);
		cond5.setRelationTp(tp);

		//assets
		assertFalse(cond1.match());
		assertFalse(cond2.match());
		assertFalse(cond3.match());
		assertFalse(cond4.match());
		assertTrue(cond5.match());
	}
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias string y condicion GT_EQ mayor qué o igual
	 */
	public void testMatchStrGTEQ() {
		//relación a comparar
		final RelationTp tp = RelationTp.GT_EQ;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refLess = new StReference("albo", ReferenceType.CONSTANT);
		final StReference refGreather = new StReference("alto", ReferenceType.CONSTANT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refGreather);
		cond4.setRelationTp(tp);

		//assets
		assertTrue(cond1.match());
		assertTrue(cond2.match());
		assertTrue(cond3.match());
		assertFalse(cond4.match());
	}
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias numéricas y condicion GT_EQ mayor qué o igual
	 */
	public void testMatchIntGTEQ() {
		//relación a comparar
		final RelationTp tp = RelationTp.GT_EQ;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refClon = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refLess = new StReference("5", ReferenceType.CONSTANT_INT);
		final StReference refOposite = new StReference("-100", ReferenceType.CONSTANT_INT);
		final StReference refGreather = new StReference("159", ReferenceType.CONSTANT_INT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refOposite);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(ref);
		cond5.setProperty2(refGreather);
		cond5.setRelationTp(tp);

		//assets
		assertTrue(cond1.match());
		assertTrue(cond2.match());
		assertTrue(cond3.match());
		assertTrue(cond4.match());
		assertFalse(cond5.match());
	}
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias string y condicion LT_EQ menor qué o igual
	 */
	public void testMatchStrLTEQ() {
		//relación a comparar
		final RelationTp tp = RelationTp.LT_EQ;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("algo", ReferenceType.CONSTANT);
		final StReference refLess = new StReference("albo", ReferenceType.CONSTANT);
		final StReference refGreather = new StReference("alto", ReferenceType.CONSTANT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refGreather);
		cond4.setRelationTp(tp);

		//assets
		assertTrue(cond1.match());
		assertTrue(cond2.match());
		assertFalse(cond3.match());
		assertTrue(cond4.match());
	}
	/**
	 * Test method for {@link org.stt.model.history.Condition#match()}.
	 * Referencias numéricas y condicion LT_EQ menor qué o igual
	 */
	public void testMatchIntLTEQ() {
		//relación a comparar
		final RelationTp tp = RelationTp.LT_EQ;
		
		//referencias iguales o diferentes
		final StReference ref = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refClon = new StReference("100", ReferenceType.CONSTANT_INT);
		final StReference refLess = new StReference("5", ReferenceType.CONSTANT_INT);
		final StReference refOposite = new StReference("-100", ReferenceType.CONSTANT_INT);
		final StReference refGreather = new StReference("159", ReferenceType.CONSTANT_INT);
		
		//condiciones de ref con todas las referencias (ella incluida)
		final Condition cond1 = new Condition();
		cond1.setProperty1(ref);
		cond1.setProperty2(ref);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(ref);
		cond2.setProperty2(refClon);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(ref);
		cond3.setProperty2(refLess);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(ref);
		cond4.setProperty2(refOposite);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(ref);
		cond5.setProperty2(refGreather);
		cond5.setRelationTp(tp);

		//assets
		assertTrue(cond1.match());
		assertTrue(cond2.match());
		assertFalse(cond3.match());
		assertFalse(cond4.match());
		assertTrue(cond5.match());
	}


	/**
	 * Test method for {@link org.stt.model.history.Condition#and(java.util.Collection)}.
	 * Check if exists
	 */
	public void testMatchEx(){
		//tipo relacion
		RelationTp tp = RelationTp.EX;
		
		//argumentos que existen o no
		final StReference refEx = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refEx2 = new StReference("158", ReferenceType.CONSTANT_INT);
		final StReference refNex = new StReference("notToBe", ReferenceType.VARIABLE);
		final StReference refNex2 = new StReference("notToBe2", ReferenceType.VARIABLE_INT);
		final StReference refNex3 = new StReference("", ReferenceType.CONSTANT);
		
		//Condiciones que cumplen o no
		final Condition cond0 = new Condition();
		cond0.setProperty1(null);
		cond0.setRelationTp(tp);
		final Condition cond1 = new Condition();
		cond1.setProperty1(refEx);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(refEx2);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(refNex);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(refNex2);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(refNex3);
		cond5.setRelationTp(tp);
		
		//asserts
		assertFalse(cond0.match());
		assertTrue(cond1.match());
		assertTrue(cond2.match());
		assertFalse(cond3.match());
		assertFalse(cond4.match());
		assertFalse(cond5.match());
	}
	/**
	 * Test method for {@link org.stt.model.history.Condition#and(java.util.Collection)}.
	 * Test Check if not exists
	 */
	public void testMatchNex(){
		//tipo relacion
		RelationTp tp = RelationTp.NEX;
		
		//argumentos que existen o no
		final StReference refEx = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refEx2 = new StReference("158", ReferenceType.CONSTANT_INT);
		final StReference refNex = new StReference("notToBe", ReferenceType.VARIABLE);
		final StReference refNex2 = new StReference("notToBe2", ReferenceType.VARIABLE_INT);
		final StReference refNex3 = new StReference("", ReferenceType.CONSTANT);
		
		//Condiciones que cumplen o no
		final Condition cond0 = new Condition();
		cond0.setProperty1(null);
		cond0.setRelationTp(tp);
		final Condition cond1 = new Condition();
		cond1.setProperty1(refEx);
		cond1.setRelationTp(tp);
		final Condition cond2 = new Condition();
		cond2.setProperty1(refEx2);
		cond2.setRelationTp(tp);
		final Condition cond3 = new Condition();
		cond3.setProperty1(refNex);
		cond3.setRelationTp(tp);
		final Condition cond4 = new Condition();
		cond4.setProperty1(refNex2);
		cond4.setRelationTp(tp);
		final Condition cond5 = new Condition();
		cond5.setProperty1(refNex3);
		cond5.setRelationTp(tp);
		
		//asserts
		assertTrue(cond0.match());
		assertFalse(cond1.match());
		assertFalse(cond2.match());
		assertTrue(cond3.match());
		assertTrue(cond4.match());
		assertTrue(cond5.match());
	}

	/**
	 * Test method for {@link org.stt.model.history.Condition#and(java.util.Collection)}.
	 */
	public void testAnd() {
		//referencias argumento de las condiciones
		final StReference ref = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refDif = new StReference("cte2", ReferenceType.CONSTANT);
		
		//condiciones que se cumplen o no
		final Condition condMatch = new Condition();
		condMatch.setProperty1(ref);
		condMatch.setProperty2(refClon);
		condMatch.setRelationTp(RelationTp.EQ);
		final Condition condMatch2 = new Condition();
		condMatch2.setProperty1(ref);
		condMatch2.setProperty2(refDif);
		condMatch2.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch = new Condition();
		condNotMatch.setProperty1(ref);
		condNotMatch.setProperty2(refClon);
		condNotMatch.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch2 = new Condition();
		condNotMatch2.setProperty1(ref);
		condNotMatch2.setProperty2(refDif);
		condNotMatch2.setRelationTp(RelationTp.EQ);
		
		//grupos de condiciones diversos
		final List<Condition> conditionsMatch = new ArrayList<Condition>();
		conditionsMatch.add(condMatch);
		conditionsMatch.add(condMatch2);
		final List<Condition> conditionsNotMatch = new ArrayList<Condition>();
		conditionsNotMatch.add(condNotMatch);
		conditionsNotMatch.add(condNotMatch2);
		final List<Condition> conditionsMixed1 = new ArrayList<Condition>();
		conditionsMixed1.addAll(conditionsMatch);
		conditionsMixed1.addAll(conditionsNotMatch);
		final List<Condition> conditionsMixed2 = new ArrayList<Condition>();
		conditionsMixed2.addAll(conditionsNotMatch);
		conditionsMixed2.addAll(conditionsMatch);
		
		//asserts
		assertTrue(Condition.and(conditionsMatch));
		assertFalse(Condition.and(conditionsNotMatch));
		assertFalse(Condition.and(conditionsMixed1));
		assertFalse(Condition.and(conditionsMixed2));
		
		//liberando
		conditionsMatch.clear();
		conditionsNotMatch.clear();
		conditionsMixed1.clear();
		conditionsMixed2.clear();
	}

	/**
	 * Test method for {@link org.stt.model.history.Condition#or(java.util.Collection)}.
	 */
	public void testOr() {
		//referencias argumento de las condiciones
		final StReference ref = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refClon = new StReference("cte1", ReferenceType.CONSTANT);
		final StReference refDif = new StReference("cte2", ReferenceType.CONSTANT);
		
		//condiciones que se cumplen o no
		final Condition condMatch = new Condition();
		condMatch.setProperty1(ref);
		condMatch.setProperty2(refClon);
		condMatch.setRelationTp(RelationTp.EQ);
		final Condition condMatch2 = new Condition();
		condMatch2.setProperty1(ref);
		condMatch2.setProperty2(refDif);
		condMatch2.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch = new Condition();
		condNotMatch.setProperty1(ref);
		condNotMatch.setProperty2(refClon);
		condNotMatch.setRelationTp(RelationTp.NEQ);
		final Condition condNotMatch2 = new Condition();
		condNotMatch2.setProperty1(ref);
		condNotMatch2.setProperty2(refDif);
		condNotMatch2.setRelationTp(RelationTp.EQ);
		
		//grupos de condiciones diversos
		final List<Condition> conditionsMatch = new ArrayList<Condition>();
		conditionsMatch.add(condMatch);
		conditionsMatch.add(condMatch2);
		final List<Condition> conditionsNotMatch = new ArrayList<Condition>();
		conditionsNotMatch.add(condNotMatch);
		conditionsNotMatch.add(condNotMatch2);
		final List<Condition> conditionsMixed1 = new ArrayList<Condition>();
		conditionsMixed1.addAll(conditionsMatch);
		conditionsMixed1.addAll(conditionsNotMatch);
		final List<Condition> conditionsMixed2 = new ArrayList<Condition>();
		conditionsMixed2.addAll(conditionsNotMatch);
		conditionsMixed2.addAll(conditionsMatch);
		
		//asserts
		assertTrue(Condition.or(conditionsMatch));
		assertFalse(Condition.or(conditionsNotMatch));
		assertTrue(Condition.or(conditionsMixed1));
		assertTrue(Condition.or(conditionsMixed2));
		
		//liberando
		conditionsMatch.clear();
		conditionsNotMatch.clear();
		conditionsMixed1.clear();
		conditionsMixed2.clear();
	}

}
