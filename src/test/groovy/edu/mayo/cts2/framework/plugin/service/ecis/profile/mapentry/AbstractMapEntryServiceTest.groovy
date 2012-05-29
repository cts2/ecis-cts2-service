package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import static org.junit.Assert.*
import org.junit.Test;

public class AbstractMapEntryServiceTest {

	@Test
	void TestStripOffVersionWith1() {
		def service = new AbstractMapEntryService()
		
		def result = service.stripOffVersion("test_with-v1")
		
		assertEquals "test_with", result
	}
	
	@Test
	void TestStripOffVersionWith999() {
		def service = new AbstractMapEntryService()
		
			def result = service.stripOffVersion("test_with-v9999")
		
		assertEquals "test_with", result
	}
}
