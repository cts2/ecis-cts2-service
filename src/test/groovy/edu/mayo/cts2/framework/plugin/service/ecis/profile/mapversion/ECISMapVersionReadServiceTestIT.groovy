package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.service.core.NameOrURI

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
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology-v1"),
			null);
		
		assertNotNull entry
	}
	
	@Test
	void TestGetMapVersionHaveHrefs() {
		def entry = service.read(
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology-v1"),
			null);
		
		assertNotNull entry
		
		assertNotNull entry.versionOf.href
		assertNotNull entry.entries

		//no codesystem profile yet//assertNotNull entry.fromCodeSystemVersion.codeSystem.href
		//no codesystem profile yet//assertNotNull entry.fromCodeSystemVersion.version.href
		
		//no codesystem profile yet//assertNotNull entry.toCodeSystemVersion.codeSystem.href
		//no codesystem profile yet//assertNotNull entry.toCodeSystemVersion.version.href
	}
	
	@Test
	void TestGetMapVersionHaveSourceAndNotation() {
		def entry = service.read(
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology-v1"),
			null);
		
		assertNotNull entry
		
		assertNotNull entry.sourceAndNotation
	}
	
	@Test
	void TestGetMapVersionValidXml() {
		def entry = service.read(
			new NameOrURI(name:"VIP_Indicator_to_CKS_Terminology-v1"),
			null);
		
		assertNotNull entry
		
		marshaller.marshal(entry, new StreamResult(new StringWriter()))
	}
	
}
