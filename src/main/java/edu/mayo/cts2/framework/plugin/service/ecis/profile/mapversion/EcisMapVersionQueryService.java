package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.core.url.UrlConstructor;
import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.core.EntityReferenceList;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.entity.EntityDescription;
import edu.mayo.cts2.framework.model.entity.EntityDirectoryEntry;
import edu.mayo.cts2.framework.model.mapversion.MapVersion;
import edu.mayo.cts2.framework.model.mapversion.MapVersionDirectoryEntry;
import edu.mayo.cts2.framework.model.service.core.NameOrURI;
import edu.mayo.cts2.framework.model.service.mapversion.types.MapRole;
import edu.mayo.cts2.framework.model.service.mapversion.types.MapStatus;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.PaginationUtils;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.meta.StandardMatchAlgorithmReference;
import edu.mayo.cts2.framework.service.profile.entitydescription.EntityDescriptionQuery;
import edu.mayo.cts2.framework.service.profile.mapversion.MapVersionQuery;
import edu.mayo.cts2.framework.service.profile.mapversion.MapVersionQueryService;

@Component
public class EcisMapVersionQueryService extends AbstractService
	implements MapVersionQueryService {
	
	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;

	@Resource
	private UrlConstructor urlConstructor;

	@Override
	public DirectoryResult<MapVersionDirectoryEntry> getResourceSummaries(
			MapVersionQuery query, 
			SortCriteria sortCriteria, 
			Page page) {
		LimitOffset limitOffset = PaginationUtils.getLimitOffset(page);
		
		List<MapVersionDirectoryEntry> entries;
		
		if(query == null || 
				query.getRestrictions() == null || 
				query.getRestrictions().getMap() == null){
			entries = mybatisMapDao.getAllMapVersions(limitOffset);
		} else {
			String mapName = query.getRestrictions().getMap().getName();
			
			String mapGuid = this.idService.getCodeSystemGuidFromName(mapName);
			
			entries = mybatisMapDao.getMapVersions(mapGuid, limitOffset);
		}
		
		this.addInHref(entries);
		
		boolean atEnd = PaginationUtils.setAtEnd(entries, limitOffset);

		return new DirectoryResult<MapVersionDirectoryEntry>(entries, atEnd);	
	}

	private void addInHref(List<MapVersionDirectoryEntry> entries) {
		if(entries == null){
			return;
		}
		
		for(MapVersionDirectoryEntry entry : entries) {
			String mapVersionHref = this.urlConstructor.
				createMapVersionUrl(
					entry.getVersionOf().getContent(),
					entry.getMapVersionName());
			
			String mapHref = this.urlConstructor.
					createMapUrl(
						entry.getVersionOf().getContent());
			
			entry.setHref(mapVersionHref);
			
			entry.getVersionOf().setHref(mapHref);
		}
	}

	@Override
	public DirectoryResult<MapVersion> getResourceList(MapVersionQuery query,
			SortCriteria sortCriteria, Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int count(MapVersionQuery query) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<? extends MatchAlgorithmReference> getSupportedMatchAlgorithms() {
		Set<MatchAlgorithmReference> returnSet = new HashSet<MatchAlgorithmReference>();
		
		returnSet.add(StandardMatchAlgorithmReference.CONTAINS.getMatchAlgorithmReference());
		returnSet.add(StandardMatchAlgorithmReference.EXACT_MATCH.getMatchAlgorithmReference());

		return returnSet;
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSearchReferences() {
		return null;
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSortReferences() {
		return null;
	}

	@Override
	public Set<PredicateReference> getKnownProperties() {
		return null;
	}

	@Override
	public DirectoryResult<EntityDirectoryEntry> mapVersionEntities(
			NameOrURI mapVersion, 
			MapRole mapRole, 
			MapStatus mapStatus,
			EntityDescriptionQuery query, 
			SortCriteria sort, 
			Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DirectoryResult<EntityDescription> mapVersionEntityList(
			NameOrURI mapVersion, 
			MapRole mapRole, 
			MapStatus mapStatus,
			EntityDescriptionQuery query, 
			SortCriteria sort, Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityReferenceList mapVersionEntityReferences(NameOrURI mapVersion,
			MapRole mapRole, 
			MapStatus mapStatus, 
			EntityDescriptionQuery query,
			SortCriteria sort,
			Page page) {
		throw new UnsupportedOperationException();
	}
}
