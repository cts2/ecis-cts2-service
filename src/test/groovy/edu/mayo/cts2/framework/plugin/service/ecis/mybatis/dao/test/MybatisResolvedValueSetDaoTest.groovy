package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.test;

import javax.annotation.Resource
import javax.sql.DataSource

import static junit.framework.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisResolvedValueSetDao

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class MybatisResolvedValueSetTest {

	@Resource
	MybatisResolvedValueSetDao dao
	
	@Resource
	DataSource datasource
	
	@Test
	void  testGetResolvedValueSetHeader(){
		def listResolvedVSH = dao.getResolvedValueSetHeader("9a06da7e-cab5-e724-e040-1c03053c10ef");
		
		assertTrue listResolvedVSH.size() > 0
	}
	
	@Test
	void  testGetResolvedValueSetSynopsis(){
		def listEntitySynopsys = dao.getResolvedValueSetSynopsis("9a06da7e-cab5-e724-e040-1c03053c10ef");
		
		assertTrue listEntitySynopsys.size() > 0
	}
}
