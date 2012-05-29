package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.model.mapversion.MapEntryDirectoryEntry;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisMapDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id.IdService;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.PaginationUtils;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.meta.StandardMatchAlgorithmReference;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryQuery;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryQueryService;

@Component
public class EcisMapEntryQueryService extends AbstractService
	implements MapEntryQueryService {
	
	private final static String VERSION_SUFFIX = "-v1";
	
	@Resource
	private MybatisMapDao mybatisMapDao;
	
	@Resource
	private IdService idService;

	@Override
	public DirectoryResult<MapEntryDirectoryEntry> getResourceSummaries(
			MapEntryQuery query, 
			SortCriteria sortCriteria, 
			Page page) {
		if(query.getRestrictions() == null || 
				query.getRestrictions().getMapVersion() == null){
			throw new UnsupportedOperationException("MapEntries currently must be constrained by MapVersion.");
		}

		String mapVersionName = this.stripOffVersion(
			query.getRestrictions().getMapVersion().getName());
		
		String guid = 
			this.idService.getCodeSystemGuidFromName(mapVersionName);
		
		LimitOffset limitOffset = PaginationUtils.getLimitOffset(page);
		List<MapEntryDirectoryEntry> entries = this.mybatisMapDao.getMapEntries(guid, limitOffset);
		
		boolean atEnd = PaginationUtils.setAtEnd(entries, limitOffset);
		
		return new DirectoryResult<MapEntryDirectoryEntry>(entries, atEnd);	
	}
	
	private String stripOffVersion(String mapVersionName) {
		return StringUtils.removeEnd(mapVersionName, VERSION_SUFFIX);
	}

	@Override
	public DirectoryResult<MapEntry> getResourceList(MapEntryQuery query,
			SortCriteria sortCriteria, Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int count(MapEntryQuery query) {
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
