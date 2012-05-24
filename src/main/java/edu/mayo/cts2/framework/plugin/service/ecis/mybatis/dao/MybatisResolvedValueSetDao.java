package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.mayo.cts2.framework.model.core.CodeSystemVersionReference;
import edu.mayo.cts2.framework.model.core.EntitySynopsis;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;

public interface MybatisResolvedValueSetDao {

	public List<EntitySynopsis> getResolvedValueSetSynopsis(
			@Param("definition_id") String definition_id, 
			@Param("limitOffset") LimitOffset limitOffset);
    
	public ResolvedValueSetHeader getResolvedValueSetHeader(String definition_id);
	
	public List<CodeSystemVersionReference> getCodeSystemVersionReferenceListByValueSetDefinition(String definition_id);

}
