package edu.mayo.cts2.framework.plugin.service.ecis.profile.map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.core.url.UrlConstructor;
import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.map.MapCatalogEntry;
import edu.mayo.cts2.framework.model.service.core.NameOrURI;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.profile.map.MapReadService;

@Component
public class EcisMapReadService extends AbstractService
	implements MapReadService {
	
	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;

	@Resource
	private UrlConstructor urlConstructor;

	@Override
	public MapCatalogEntry read(
			NameOrURI identifier,
			ResolvedReadContext readContext) {
		
		String mapName = identifier.getName();
		
		String mapGuid = 
			this.idService.getCodeSystemGuidFromName(mapName);
		
		return this.addInHref(
			this.mybatisMapDao.getMap(mapGuid));
	}
	
	private MapCatalogEntry addInHref(MapCatalogEntry entry) {
		if(entry == null){
			return null;
		}
	
		//TODO: Need to add this to the UrlConstructor
		String mapVersionsHref = this.urlConstructor.createMapUrl(entry.getMapName()) + "/versions";
		
		entry.setVersions(mapVersionsHref);
		
		String mapHref = this.urlConstructor.createMapUrl(entry.getMapName());
		entry.getCurrentVersion().getMap().setHref(mapHref);
		
		entry.getCurrentVersion().getMapVersion().setHref(
				this.urlConstructor.createMapVersionUrl(
						entry.getMapName(), 
						entry.getCurrentVersion().getMapVersion().getContent()));
		
		return entry;
	}

	@Override
	public boolean exists(
			NameOrURI identifier, 
			ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

	
}
