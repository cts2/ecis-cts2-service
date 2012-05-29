package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry

import javax.annotation.Resource;

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.model.util.ModelUtils
import edu.mayo.cts2.framework.service.command.restriction.MapEntryQueryServiceRestrictions
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryQuery

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISMapEntryQueryServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisMapEntryQueryService service
	
	@Test
	void TestGetEntriesWithMapVersionName() {
		def summaries = service.getResourceSummaries(
			[
				getRestrictions: {
					new MapEntryQueryServiceRestrictions(mapVersion: ModelUtils.nameOrUriFromName("Admission_Type_to_CKS_Terminology-v1"))
				}
			] as MapEntryQuery, 
			null, 
			new Page());
		
		assertNotNull summaries
		assertTrue summaries.entries.size() > 0
	}
	
	@Test
	void TestGetEntriesWithMapVersionNameHaveHrefs() {
		def summaries = service.getResourceSummaries(
			[
				getRestrictions: {
					new MapEntryQueryServiceRestrictions(mapVersion: ModelUtils.nameOrUriFromName("Admission_Type_to_CKS_Terminology-v1"))
				}
			] as MapEntryQuery,
			null,
			new Page());
		
		assertNotNull summaries
		assertTrue summaries.entries.size() > 0
		
		summaries.entries.each {
			assertNotNull it.href
		}
		
	}
	
}
