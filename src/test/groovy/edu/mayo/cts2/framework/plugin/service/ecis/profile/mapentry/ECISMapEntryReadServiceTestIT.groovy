package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry

import javax.annotation.Resource;

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
	
}
