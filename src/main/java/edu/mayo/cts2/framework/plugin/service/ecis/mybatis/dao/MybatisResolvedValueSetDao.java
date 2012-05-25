package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import edu.mayo.cts2.framework.model.core.CodeSystemVersionReference;
import edu.mayo.cts2.framework.model.core.EntitySynopsis;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;

public interface MybatisResolvedValueSetDao {

	public List<EntitySynopsis> getResolvedValueSetSynopsis(
			@Param("entities") Set<String> entities, 
			@Param("limitOffset") LimitOffset limitOffset);
    
	public ResolvedValueSetHeader getResolvedValueSetHeader(
			@Param("valueSetName") String valueSetName);
	
	public List<CodeSystemVersionReference> getCodeSystemVersionReferenceListByValueSetDefinition(
			String definition_id);

	public List<TransitiveResult> resolveTransitiveRelationsFiveLevels(
			@Param("entities") Set<String> entities,
			@Param("predicate") String predicateGuid);
	
	public Set<String> resolveOneLevelRelations(
			@Param("entities") Set<String> entities,
			@Param("predicate") String predicateGuid);
	
	public Set<ValueSetDefinitionEntryInfo> getEntries(
			@Param("valueSetName") String valueSetName);
}
