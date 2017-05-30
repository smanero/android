/**
 * 
 */
package org.stt.test.service;

import org.stt.service.SttAssetsService;

import android.test.AndroidTestCase;

/**
 * @author Gaizka
 *
 */
public class TestAssetsService extends AndroidTestCase {

	private SttAssetsService assetService;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		this.assetService = new SttAssetsService(this.getContext());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.assetService.close();
		this.assetService = null;
		super.tearDown();
	}

	public void TestOpen(){
		final String str = this.assetService.getStringFromAssetFile("assetSqlMock.sql");
		assertTrue(str.startsWith("INSERT INTO"));
	}
}
