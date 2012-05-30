package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.core.url.UrlConstructor;
import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.core.CodeSystemVersionReference;
import edu.mayo.cts2.framework.model.core.VersionTagReference;
import edu.mayo.cts2.framework.model.mapversion.MapVersion;
import edu.mayo.cts2.framework.model.service.core.NameOrURI;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.profile.mapversion.MapVersionReadService;

@Component
public class EcisMapVersionReadService extends AbstractService
	implements MapVersionReadService {
	
	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;

	@Resource
	private UrlConstructor urlConstructor;

	@Override
	public MapVersion read(
			NameOrURI identifier,
			ResolvedReadContext readContext) {
		
		String mapName = identifier.getName();
		
		String mapGuid = 
			this.idService.getCodeSystemGuidFromName(mapName);
		
		return this.addInHref(
			this.mybatisMapDao.getMapVersion(mapGuid));
	}
	
	private MapVersion addInHref(MapVersion entry) {
		if(entry == null){
			return null;
		}
	
		String mapEntriesHref = this.urlConstructor.
			createMapVersionUrl(
				entry.getVersionOf().getContent(),
				entry.getMapVersionName()) + "/entries";
		
		String mapHref = this.urlConstructor.
				createMapUrl(
					entry.getVersionOf().getContent());
		
		entry.setEntries(mapEntriesHref);
		
		entry.getVersionOf().setHref(mapHref);
		
		this.setCodeSystemVersionReferenceHrefs(
			entry.getFromCodeSystemVersion());
		
		this.setCodeSystemVersionReferenceHrefs(
			entry.getToCodeSystemVersion());

		return entry;
	}
	
	private void setCodeSystemVersionReferenceHrefs(CodeSystemVersionReference ref){
		ref.
			getCodeSystem().
				setHref(
					this.urlConstructor.createCodeSystemUrl(ref.
						getCodeSystem().getContent()));
	
		ref.
			getVersion().
				setHref(
					this.urlConstructor.createCodeSystemVersionUrl(
							ref.getCodeSystem().getContent(),
							ref.getVersion().getContent()));
	}

	@Override
	public boolean exists(
			NameOrURI identifier, 
			ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MapVersion readByTag(NameOrURI parentIdentifier,
			VersionTagReference tag, ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsByTag(NameOrURI parentIdentifier,
			VersionTagReference tag, ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<VersionTagReference> getSupportedTags() {
		return null;
	}
	
}
