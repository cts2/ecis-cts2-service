package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import edu.mayo.cts2.framework.model.codesystem.CodeSystemCatalogEntry;

/*
 * Method names will correspond to 'id's in the MyBatis mapping
 * files. 
 * 
 * See 'testMapper.xml'
 */
public interface MybatisCodeSystemDao {

	public CodeSystemCatalogEntry getCodeSystemByName(String codeSystemName);
	
	public CodeSystemCatalogEntry getCodeSystemByUri(String uri);
}
