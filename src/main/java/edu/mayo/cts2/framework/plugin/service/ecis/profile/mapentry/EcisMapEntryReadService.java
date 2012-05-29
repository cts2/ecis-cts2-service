package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryReadService;
import edu.mayo.cts2.framework.service.profile.mapentry.name.MapEntryReadId;

@Component
public class EcisMapEntryReadService extends AbstractMapEntryService
	implements MapEntryReadService {

	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;

	@Override
	public MapEntry read(
			MapEntryReadId identifier,
			ResolvedReadContext readContext) {
		
		String mapVersonName = this.stripOffVersion(
				identifier.getMapVersion().getName());
		
		String guid = this.idService.getCodeSystemGuidFromName(mapVersonName);
		
		String mapFromCode = identifier.getEntityName().getName();
		
		return this.mybatisMapDao.getMapEntryById(guid, mapFromCode);
	}

	@Override
	public boolean exists(
			MapEntryReadId identifier,
			ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

}
