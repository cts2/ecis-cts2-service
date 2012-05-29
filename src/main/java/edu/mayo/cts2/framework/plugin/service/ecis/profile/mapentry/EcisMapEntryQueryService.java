package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import java.util.Set;

import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.model.mapversion.MapEntryDirectoryEntry;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryQuery;
import edu.mayo.cts2.framework.service.profile.mapentry.MapEntryQueryService;

public class EcisMapEntryQueryService extends AbstractService
	implements MapEntryQueryService {

	@Override
	public DirectoryResult<MapEntryDirectoryEntry> getResourceSummaries(
			MapEntryQuery query, 
			SortCriteria sortCriteria, 
			Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectoryResult<MapEntry> getResourceList(MapEntryQuery query,
			SortCriteria sortCriteria, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(MapEntryQuery query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<? extends MatchAlgorithmReference> getSupportedMatchAlgorithms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSearchReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSortReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PredicateReference> getKnownProperties() {
		// TODO Auto-generated method stub
		return null;
	}
}
