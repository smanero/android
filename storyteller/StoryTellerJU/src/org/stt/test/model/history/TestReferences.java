/**
 * 
 */
package org.stt.test.model.history;

import org.stt.model.history.StReference;
import org.stt.model.history.StReference.ReferenceType;

import junit.framework.TestCase;

/**
 * @author Gaizka
 *
 */
public class TestReferences extends TestCase {


	/**
	 * Test method for {@link org.stt.model.history.StReference#hashCode()}.
	 */
	public void testHashCode() {
		final StReference ref = new StReference("ref1", ReferenceType.PAGE),
				refClon =  new StReference("ref1", ReferenceType.PAGE),
				refCasiClon = new StReference("ref1", ReferenceType.DISPACHER),
				refDif = new StReference("dif", ReferenceType.PAGE);

		assertEquals(ref.hashCode(), refClon.hashCode());
		assertTrue(ref.hashCode() != refCasiClon.hashCode());
		assertTrue(ref.hashCode() != refDif.hashCode());
	}

	/**
	 * Test method for {@link org.stt.model.history.StReference#isPage()}.
	 */
	public void testIsPage() {
		final StReference ref = new StReference("ref", ReferenceType.PAGE)
		, ref2 = new StReference("ref", ReferenceType.DISPACHER)
		, ref3 = new StReference("ref", ReferenceType.GAME_INT_PROPERTY)
		, ref4 = new StReference("ref", ReferenceType.GAME_PROPERTY)
		, ref5 = new StReference("ref", ReferenceType.SDF_PROPERTY)
		, ref6 = new StReference("ref", ReferenceType.CONSTANT_INT)
		, ref7 = new StReference("ref", ReferenceType.VARIABLE);
		
		assertTrue(ref.isPage());
		assertFalse(ref2.isPage());
		assertFalse(ref3.isPage());
		assertFalse(ref4.isPage());
		assertFalse(ref5.isPage());
		assertFalse(ref6.isPage());
		assertFalse(ref7.isPage());
	}

	/**
	 * Test method for {@link org.stt.model.history.StReference#isDispacher()}.
	 */
	public void testIsDispacher() {
		final StReference ref = new StReference("ref", ReferenceType.PAGE)
		, ref2 = new StReference("ref", ReferenceType.DISPACHER)
		, ref3 = new StReference("ref", ReferenceType.GAME_INT_PROPERTY)
		, ref4 = new StReference("ref", ReferenceType.GAME_PROPERTY)
		, ref5 = new StReference("ref", ReferenceType.SDF_PROPERTY)
		, ref6 = new StReference("ref", ReferenceType.CONSTANT_INT)
		, ref7 = new StReference("ref", ReferenceType.VARIABLE);

		assertFalse(ref.isDispacher());
		assertTrue(ref2.isDispacher());
		assertFalse(ref3.isDispacher());
		assertFalse(ref4.isDispacher());
		assertFalse(ref5.isDispacher());
		assertFalse(ref6.isDispacher());
		assertFalse(ref7.isDispacher());
	}

	/**
	 * Test method for {@link org.stt.model.history.StReference#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() {
		final StReference ref = new StReference("ref1", ReferenceType.PAGE),
			refClon =  new StReference("ref1", ReferenceType.PAGE),
			refCasiClon = new StReference("ref1", ReferenceType.DISPACHER),
			refDif = new StReference("dif", ReferenceType.PAGE);
		final Integer noref = Integer.valueOf(0);

		assertTrue(ref.equals(ref));
		assertTrue(ref.equals(refClon));
		assertTrue(refClon.equals(ref));
		assertFalse(ref.equals(refCasiClon));
		assertFalse(refCasiClon.equals(ref));
		assertFalse(ref.equals(refDif));
		assertFalse(refDif.equals(ref));
		assertFalse(ref.equals(noref));
		assertFalse(noref.equals(ref));
	}

}
