package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.test;

import javax.annotation.Resource
import javax.sql.DataSource

import static junit.framework.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.jdbc.SimpleJdbcTestUtils

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisCodeSystemDao

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class MybatisCodeSystemDaoTest {

	@Resource
	MybatisCodeSystemDao dao
	
	@Resource
	DataSource datasource


	
	@Test
	void  TestGetCodeSystemByName(){
		def codeSystem = dao.getCodeSystemByName("CKS_Terminology");
		
		assertEquals "CKS_Terminology", codeSystem.getCodeSystemName()
	}
	
	@Test
	void  TestGetCodeSystemByUri(){
		def codeSystem = dao.getCodeSystemByUri("urn:uuid:29fd187c-1f56-4334-8b90-0d03c96a7b71");
		
		assertEquals "urn:uuid:29fd187c-1f56-4334-8b90-0d03c96a7b71", codeSystem.getAbout()
	}
}
