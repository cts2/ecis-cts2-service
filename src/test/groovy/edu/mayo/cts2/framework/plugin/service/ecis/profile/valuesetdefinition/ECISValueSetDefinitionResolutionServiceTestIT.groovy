package edu.mayo.cts2.framework.plugin.service.ecis.profile.valuesetdefinition

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult

import static org.junit.Assert.*
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.model.util.ModelUtils
import edu.mayo.cts2.framework.service.profile.resolvedvalueset.name.ResolvedValueSetReadId
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.name.ValueSetDefinitionReadId

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISValueSetDefinitionResolutionServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisValueSetDefinitionResolutionService resolution
	
	@Test
	void TestResolveDefinition(){

		def dir = resolution.resolveDefinition(
			new ValueSetDefinitionReadId("1",
				ModelUtils.nameOrUriFromName("Dietary Formula Strength Value Set")),
			null,
			null,
			null,
			null,
			null,
			new Page())
		
		assertNotNull dir
		assertTrue dir.getEntries().size() > 0

	}
	
	@Test
	void TestGetResolutionWrongValueSetName(){

			def dir = resolution.resolveDefinition(
			new ValueSetDefinitionReadId("1",
				ModelUtils.nameOrUriFromName("__WRONG__")),
			null,
			null,
			null,
			null,
			null,
			new Page())
		
		assertNull dir
	}
	
	@Test
	void TestGetResolutionHasResolutionOf(){

		def dir = resolution.resolveDefinition(
			new ValueSetDefinitionReadId("1",
				ModelUtils.nameOrUriFromName("Dietary Formula Strength Value Set")),
			null,
			null,
			null,
			null,
			null,
			new Page())
		
		assertNotNull dir
		assertTrue dir.getEntries().size() > 0

		
		assertTrue dir.resolvedValueSetHeader.resolvedUsingCodeSystem.size() > 0
	}
	

	
	@Test
	void TestGetResolutionValidXML(){

		def dir = resolution.resolveDefinition(
			new ValueSetDefinitionReadId("1",
				ModelUtils.nameOrUriFromName("Dietary_Formula_Strength_Value_Set")),
			null,
			null,
			null,
			null,
			null,
			new Page())
		
		assertNotNull dir
		assertTrue dir.getEntries().size() > 0

		dir.entries.each {
			marshaller.marshal(it, new StreamResult(new StringWriter()))
		}
	}
	
	@Test
	void TestGetResolvedValueSetHeader(){
		def header = resolution.getResolvedValueSetHeader(
				"Dietary Formula Strength Value Set")
		
		assertNotNull header
	}
	
}
