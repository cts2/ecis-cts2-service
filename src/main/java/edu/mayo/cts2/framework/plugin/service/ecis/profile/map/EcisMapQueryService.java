package edu.mayo.cts2.framework.plugin.service.ecis.profile.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.core.url.UrlConstructor;
import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.map.MapCatalogEntry;
import edu.mayo.cts2.framework.model.map.MapCatalogEntrySummary;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.PaginationUtils;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.meta.StandardMatchAlgorithmReference;
import edu.mayo.cts2.framework.service.profile.map.MapQuery;
import edu.mayo.cts2.framework.service.profile.map.MapQueryService;

@Component
public class EcisMapQueryService extends AbstractService
	implements MapQueryService {
	
	@Resource
	private MybatisMapDao mybatisMapDao;

	@Resource
	private UrlConstructor urlConstructor;

	@Override
	public DirectoryResult<MapCatalogEntrySummary> getResourceSummaries(
			MapQuery query, 
			SortCriteria sortCriteria, 
			Page page) {
		LimitOffset limitOffset = PaginationUtils.getLimitOffset(page);
		
		List<MapCatalogEntrySummary> entries = mybatisMapDao.getMaps(limitOffset);
		
		this.addInHref(entries);
		
		boolean atEnd = PaginationUtils.setAtEnd(entries, limitOffset);

		return new DirectoryResult<MapCatalogEntrySummary>(entries, atEnd);	
	}

	private void addInHref(List<MapCatalogEntrySummary> entries) {
		if(entries == null){
			return;
		}
		
		for(MapCatalogEntrySummary entry : entries) {
			String mapHref = this.urlConstructor.
				createMapUrl(
					entry.getMapName());
			
			entry.setHref(mapHref);
			
			entry.getCurrentVersion().getMap().setHref(mapHref);
			
			entry.getCurrentVersion().getMapVersion().setHref(
					this.urlConstructor.createMapVersionUrl(
							entry.getMapName(), 
							entry.getCurrentVersion().getMapVersion().getContent()));
		}
	}

	@Override
	public DirectoryResult<MapCatalogEntry> getResourceList(MapQuery query,
			SortCriteria sortCriteria, Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int count(MapQuery query) {
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
}
