package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import javax.annotation.Resource

import static junit.framework.Assert.*

import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])

class MybatisMapDaoTest {

	@Resource
	MybatisMapDao dao

	@Test
	void  TestGetMapEntries(){
		def entries = dao.getMapEntries("9a06da7e-775d-e724-e040-1c03053c10ef", new LimitOffset(10,0));
		
		assertNotNull entries
		assertTrue entries.size() > 0
	}
	
}
