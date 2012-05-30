package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion

import javax.annotation.Resource;

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.model.service.core.NameOrURI
import edu.mayo.cts2.framework.service.profile.map.MapQuery

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISMapVersionReadServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisMapVersionReadService service
	
	@Test
	void TestGetMapVersion() {
		def entry = service.read(
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology"),
			null);
		
		assertNotNull entry
	}
	
	@Test
	void TestGetMapVersionHaveHrefs() {
		def entry = service.read(
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology"),
			null);
		
		assertNotNull entry
		
		assertNotNull entry.versionOf.href
		assertNotNull entry.entries

		assertNotNull entry.fromCodeSystemVersion.codeSystem.href
		assertNotNull entry.fromCodeSystemVersion.version.href
		
		assertNotNull entry.toCodeSystemVersion.codeSystem.href
		assertNotNull entry.toCodeSystemVersion.version.href
	}
	
}
