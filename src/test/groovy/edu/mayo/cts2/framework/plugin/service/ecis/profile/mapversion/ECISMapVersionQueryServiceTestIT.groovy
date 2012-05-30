package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion

import javax.annotation.Resource;

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.plugin.service.ecis.profile.map.EcisMapQueryService
import edu.mayo.cts2.framework.service.profile.map.MapQuery
import edu.mayo.cts2.framework.service.profile.mapversion.MapVersionQuery

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISMapVersionQueryServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisMapVersionQueryService service
	
	@Test
	void TestGetMapVersions() {
		def summaries = service.getResourceSummaries(
			null as MapVersionQuery,
			null,
			new Page());
		
		assertNotNull summaries
		assertTrue summaries.entries.size() > 0
	}
	
	@Test
	void TestGetMapsHaveHrefs() {
		def summaries = service.getResourceSummaries(
			null as MapQuery,
			null,
			new Page());
		
		assertNotNull summaries
		assertTrue summaries.entries.size() > 0
		
		summaries.entries.each {
			assertNotNull it.href
			assertNotNull it.versionOf.href	
		}
	}
	
}
