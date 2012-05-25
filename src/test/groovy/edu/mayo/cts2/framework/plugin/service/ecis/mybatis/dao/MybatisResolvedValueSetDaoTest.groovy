package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import javax.annotation.Resource
import javax.sql.DataSource

import static junit.framework.Assert.*

import org.apache.log4j.Logger
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import edu.mayo.cts2.framework.model.command.Page
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisResolvedValueSetDao
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.PaginationUtils

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class MybatisResolvedValueSetTest {

	@Resource
	MybatisResolvedValueSetDao dao
	
	@Resource
	DataSource datasource
	static final Logger logger = Logger.getLogger(MybatisResolvedValueSetTest.class);
	@Before
	void setLogging() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	
	@Test
	void  testGetResolvedValueSetHeader(){
		logger.info("testGetResolvedValueSetHeader");

		def listResolvedVSH = dao.getResolvedValueSetHeader("Dietary_Formula_Strength_Value_Set");
		
		assertNotNull listResolvedVSH
	}
	
	@Test
	void  testGetResolvedValueSetSynopsis(){
		def page = new Page(); 
		
		def listEntitySynopsys = dao.getResolvedValueSetSynopsis(
			["4887c412-b7c0-4130-09fc-0ce34632216e"] as Set, PaginationUtils.getLimitOffset(page));
		
		assertTrue listEntitySynopsys.size() > 0
	}
	
	
	@Test
	void  testGetCodeSystemVersionReferenceByValueSetDefinition(){
		def listCodeSystemVersionRefs = dao.getCodeSystemVersionReferenceListByValueSetDefinition("Dietary_Formula_Strength_Value_Set");
		
		assertTrue listCodeSystemVersionRefs.size() > 0
	}
	
	@Test
	void  testResolveTransitiveRelationsFiveLevels(){
		def transitiveResult = dao.resolveTransitiveRelationsFiveLevels(
			["1d529776-be3a-4cba-a277-863ca9bab696"] as Set, 
			"9a06da72-91fb-e724-e040-1c03053c10ef");
		
		assertNotNull transitiveResult
		assertTrue transitiveResult.size() > 0
	}
	
	
	@Test
	void  testGetEntries(){
		def result = dao.getEntries("Dietary_Formula_Strength_Value_Set");
		
		assertNotNull result
		assertTrue result.size() > 0
	}

	
}
