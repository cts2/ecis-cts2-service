package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import javax.annotation.Resource
import javax.xml.transform.stream.StreamResult

import static junit.framework.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import edu.mayo.cts2.framework.core.xml.Cts2Marshaller;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])

class MybatisMapDaoTest {
	
	@Resource
	Cts2Marshaller marshaller

	@Resource
	MybatisMapDao dao

	@Test
	void  TestGetMapEntryById(){
		def entry = dao.getMapEntryById("9a06da7e-775d-e724-e040-1c03053c10ef", ">");
		
		assertNotNull entry
	}
	
	@Test
	void  TestGetMapEntryValidXml(){
		def entry = dao.getMapEntryById("9a06da7e-775d-e724-e040-1c03053c10ef", ">");
		
		assertNotNull entry

		marshaller.marshal(entry, new StreamResult(new StringWriter()))
	}
	
	@Test
	void  TestGetMapEntryHasMapSource(){
		def entry = dao.getMapEntryById("9a06da7e-775d-e724-e040-1c03053c10ef", ">");

		def mapFrom = entry.mapFrom
		
		assertNotNull mapFrom
		assertNotNull mapFrom.name
		assertNotNull mapFrom.namespace
		assertNotNull mapFrom.uri
	}
	
	@Test
	void  TestGetMapEntryHasMapTarget(){
		def entry = dao.getMapEntryById("9a06da7e-775d-e724-e040-1c03053c10ef", ">");

		def mapTarget = entry.mapSet[0].mapTarget[0].mapTo
		
		assertNotNull mapTarget
		assertNotNull mapTarget.name
		assertNotNull mapTarget.namespace
		assertNotNull mapTarget.uri
	}
	
	@Test
	void  TestGetMapEntryHasAssertedBy(){
		def entry = dao.getMapEntryById("9a06da7e-775d-e724-e040-1c03053c10ef", ">");
		
		assertNotNull entry.assertedBy
	}
	
	@Test
	void  TestGetMapEntries(){
		def entries = dao.getMapEntries("9a06da7e-775d-e724-e040-1c03053c10ef", new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
	}
	
	@Test
	void  TestGetAllMapEntries(){
		def entries = dao.getAllMapEntries(new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
	}
	
	@Test
	void  TestGetMapEntriesValidXml(){
		def entries = dao.getMapEntries("9a06da7e-775d-e724-e040-1c03053c10ef", new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
		
		entries.each {
			marshaller.marshal(it, new StreamResult(new StringWriter()))
		}
	}

	@Test
	void  TestGetMapEntriesHasAssertedBy(){
		def entries = dao.getMapEntries("9a06da7e-775d-e724-e040-1c03053c10ef", new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
		
		entries.each {
			assertNotNull it.assertedBy
		}
	}
	
	@Test
	void  TestGetMaps(){
		def entries = dao.getMaps(new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
	}
	
	@Test
	void  TestGetMapsHasFromAndTo(){
		def entries = dao.getMaps(new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
		
		entries.each {
			assertNotNull it.fromCodeSystem
			assertNotNull it.toCodeSystem
		}
	}
	
	@Test
	void  TestGetMapsHasCurrentVersion(){
		def entries = dao.getMaps(new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
		
		entries.each {
			assertNotNull it.currentVersion
		}
	}
	
	@Test
	void  TestGetMapsHaveDescriptions(){
		def entries = dao.getMaps(new LimitOffset(10,0));

		assertNotNull entries
		assertTrue entries.size() > 0
		
		entries.each {
			it.href
			assertNotNull it.resourceSynopsis.value.content	
		}
	}
	
	@Test
	void  TestGetAllMapVersions(){
		def versions = dao.getAllMapVersions(new LimitOffset(10,0));

		assertNotNull versions
		assertTrue versions.size() > 0
	}
}
