package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.core.url.UrlConstructor;
import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.core.MapReference;
import edu.mayo.cts2.framework.model.core.NameAndMeaningReference;
import edu.mayo.cts2.framework.model.core.URIAndEntityName;
import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.model.mapversion.MapSet;
import edu.mayo.cts2.framework.model.mapversion.MapTarget;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion.MapVersionUtils;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryReadService;
import edu.mayo.cts2.framework.service.profile.mapentry.name.MapEntryReadId;

@Component
public class EcisMapEntryReadService extends AbstractService
	implements MapEntryReadService {

	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;
	
	@Resource
	private UrlConstructor urlConstructor;

	@Override
	public MapEntry read(
			MapEntryReadId identifier,
			ResolvedReadContext readContext) {
		
		String mapVersonName = MapVersionUtils.stripOffVersion(
				identifier.getMapVersion().getName());
		
		String guid = this.idService.getCodeSystemGuidFromName(mapVersonName);
		
		String mapFromCode = identifier.getEntityName().getName();
		
		return 
			this.addInHrefs(
				this.addInDefaultTargetUris(
						this.mybatisMapDao.getMapEntryById(guid, mapFromCode)));
	}
	
	private MapEntry addInHrefs(MapEntry mapEntry){
		if(mapEntry == null){
			return null;
		}
		
		MapReference map = mapEntry.getAssertedBy().getMap();
		map.setHref(
			this.urlConstructor.createMapUrl(map.getContent())
		);
		
		NameAndMeaningReference mapVersion = mapEntry.getAssertedBy().getMapVersion();
		mapVersion.setHref(
			this.urlConstructor.createMapVersionUrl(map.getContent(), mapVersion.getContent())
		);
		
		return mapEntry;
	}
	
	private MapEntry addInDefaultTargetUris(MapEntry mapEntry){
		if(mapEntry == null){
			return null;
		}
		
		for(MapSet mapSet : mapEntry.getMapSet()){
			for(MapTarget mapTarget : mapSet.getMapTarget()){
				URIAndEntityName target = mapTarget.getMapTo();
				
				String uri = target.getUri();
				if(StringUtils.isBlank(uri)){
					
					target.setUri(
						this.idService.getCodeSystemUriFromName(target.getNamespace()) + ":" + target.getName()
					);
				}
			}
		}
		
		return mapEntry;
	}

	@Override
	public boolean exists(
			MapEntryReadId identifier,
			ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

}
