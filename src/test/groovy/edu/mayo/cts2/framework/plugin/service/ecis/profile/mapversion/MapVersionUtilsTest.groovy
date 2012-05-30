package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion;

import static org.junit.Assert.*
import org.junit.Test;

public class MapVersionUtilsTest {

	@Test
	void TestStripOffVersionWith1() {

		def result = MapVersionUtils.stripOffVersion("test_with-v1")
		
		assertEquals "test_with", result
	}
	
	@Test
	void TestStripOffVersionWith999() {
		
		def result = MapVersionUtils.stripOffVersion("test_with-v9999")
		
		assertEquals "test_with", result
	}
}
