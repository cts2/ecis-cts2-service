package edu.mayo.cts2.framework.plugin.service.demosql.mybatis.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisCodeSystemDao;

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["/test-ecis-cts2-context.xml"])
class MybatisCodeSystemDaoTest {

	@Resource
	MybatisCodeSystemDao dao
	
	@Resource
	DataSource datasource

	@Before
	void loadData(){
		SimpleJdbcTestUtils.executeSqlScript(
			new SimpleJdbcTemplate(datasource), new ClassPathResource("database/test-dataload.sql"), false)
	}
	
	@After
	void dropData(){
		new JdbcTemplate(datasource).execute("DROP TABLE codesystems")
	}
	
	@Test
	void  TestGetCodeSystemByName(){
		def codeSystem = dao.getCodeSystemByName("myTest");
		
		assertEquals "myTest", codeSystem.getCodeSystemName()
	}
	
	@Test
	void  TestGetCodeSystemByUri(){
		def codeSystem = dao.getCodeSystemByUri("http://test/cs");
		
		assertEquals "http://test/cs", codeSystem.getAbout()
	}
}
