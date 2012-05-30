package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.core.ScopedEntityName
import edu.mayo.cts2.framework.model.util.ModelUtils
import edu.mayo.cts2.framework.service.profile.mapentry.name.MapEntryReadId

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISMapEntryReadServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisMapEntryReadService service
	
	@Test
	void TestReadMapEntry() {
		def entry = service.read(
			new MapEntryReadId(
				ModelUtils.entityNameOrUriFromName(
				new ScopedEntityName(name:"2")),
				ModelUtils.nameOrUriFromName("Admission_Type_to_CKS_Terminology-v1")
				),
			null
		)
		
		assertNotNull entry
	}
	
	@Test
	void TestReadMapEntryWithGuid() {
		def entry = service.read(
			new MapEntryReadId(
				ModelUtils.entityNameOrUriFromName(
				new ScopedEntityName(name:"b7f856ac-646a-4f60-8422-c6e71fcd6249")),
				ModelUtils.nameOrUriFromName("CKS_Terminology_to_HL7_Role_Code_Codes-v1")
				),
			null
		)
		
		assertNotNull entry
	}
	
	@Test
	void TestReadMapEntryWithGuidValidXml() {
		def entry = service.read(
			new MapEntryReadId(
				ModelUtils.entityNameOrUriFromName(
				new ScopedEntityName(name:"41f7a9dc-78a0-412e-a079-10262f8842f9")),
				ModelUtils.nameOrUriFromName("CKS_Terminology_to_HL7_URL_Scheme_Codes-v1")
				),
			null
		)
		
		assertNotNull entry
		
		marshaller.marshal(entry, new StreamResult(new StringWriter()))
	}
	
}
