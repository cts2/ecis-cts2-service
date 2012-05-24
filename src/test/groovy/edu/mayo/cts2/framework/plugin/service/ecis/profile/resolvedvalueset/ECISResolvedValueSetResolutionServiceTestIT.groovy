package edu.mayo.cts2.framework.plugin.service.ecis.profile.resolvedvalueset

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class ECISResolvedValueSetResolutionServiceTestIT {
	
	@Resource
	Cts2Marshaller marshaller
	
	@Resource
	EcisResolvedValueSetResolutionService resolution
	
	@Test
	void TestGetResolution(){

		def dir = resolution.getResolution(
			new ResolvedValueSetReadId("9a06da7e-cab5-e724-e040-1c03053c10ef",
				ModelUtils.nameOrUriFromName("9a06da7e-cab5-e724-e040-1c03053c10ef"),null),
			null,
			new Page())
		
		assertNotNull dir
		assertTrue dir.getEntries().size() > 0

	}
	
	@Test
	void TestGetResolutionWrongValueSetName(){

		def dir = resolution.getResolution(
			new ResolvedValueSetReadId("__WRONG__",ModelUtils.nameOrUriFromName("BRO-WRONG"),null),null,new Page())
		
		assertNull dir

	}
	
	@Test
	void TestGetResolutionHasResolutionOf(){

		def dir = resolution.getResolution(
			new ResolvedValueSetReadId("9a06da7e-cab5-e724-e040-1c03053c10ef",ModelUtils.nameOrUriFromName("9a06da7e-cab5-e724-e040-1c03053c10ef"),null),
			null,
			new Page())
		
		assertTrue dir.resolvedValueSetHeader.resolvedUsingCodeSystem.size() > 0
	}
	

	
	@Test
	void TestGetResolutionValidXML(){

		def dir = resolution.getResolution(
			new ResolvedValueSetReadId("41011",ModelUtils.nameOrUriFromName("9a06da7e-cab5-e724-e040-1c03053c10ef"),null),
			null,
			new Page())
		
		marshaller.marshal(dir, new StreamResult(new StringWriter()))
	}
	
	@Test
	void TestGetResolvedValueSetHeader(){
		def header = resolution.getResolvedValueSetHeader(
				"9a06da7e-cab5-e724-e040-1c03053c10ef")
		
		assertNotNull header
	}
	
}
