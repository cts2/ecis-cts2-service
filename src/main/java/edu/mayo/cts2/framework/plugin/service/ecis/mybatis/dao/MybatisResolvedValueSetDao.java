package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.List;

import edu.mayo.cts2.framework.model.core.CodeSystemVersionReference;
import edu.mayo.cts2.framework.model.core.EntitySynopsis;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader;

public interface MybatisResolvedValueSetDao {

	public List<EntitySynopsis> getResolvedValueSetSynopsis(String definition_id);
    public List<ResolvedValueSetHeader> getResolvedValueSetHeader(String definition_id);
	public List<CodeSystemVersionReference> getCodeSystemVersionReferenceListByValueSetDefinition(String definition_id);

}
