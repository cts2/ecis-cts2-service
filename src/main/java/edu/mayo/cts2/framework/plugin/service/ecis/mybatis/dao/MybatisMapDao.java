package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.mayo.cts2.framework.model.map.MapCatalogEntry;
import edu.mayo.cts2.framework.model.map.MapCatalogEntrySummary;
import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.model.mapversion.MapEntryDirectoryEntry;
import edu.mayo.cts2.framework.model.mapversion.MapVersion;
import edu.mayo.cts2.framework.model.mapversion.MapVersionDirectoryEntry;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;

/*
 * Method names will correspond to 'id's in the MyBatis mapping
 * files. 
 * 
 * See 'codeSystemMapper.xml'
 */
public interface MybatisMapDao {

	public MapEntry getMapEntryById(
			@Param("mapGuid") String mapGuid,
			@Param("mapFromCode") String mapFromCode);
	
	public List<MapEntryDirectoryEntry> getAllMapEntries(
			@Param("limitOffset")  LimitOffset limtOffset);
	
	public List<MapEntryDirectoryEntry> getMapEntries(
			@Param("mapGuid") String mapGuid,
			@Param("limitOffset")  LimitOffset limtOffset);
	
	public List<MapCatalogEntrySummary> getMaps(
			@Param("limitOffset")  LimitOffset limtOffset);
	
	public MapCatalogEntry getMap(
			@Param("mapGuid") String mapGuid);
	
	public MapVersion getMapVersion(
			@Param("mapGuid") String mapGuid);
	
	public List<MapVersionDirectoryEntry> getMapVersions(
			@Param("mapGuid") String mapGuid,
			@Param("limitOffset")  LimitOffset limtOffset);
	
	public List<MapVersionDirectoryEntry> getAllMapVersions(
			@Param("limitOffset")  LimitOffset limtOffset);

}
