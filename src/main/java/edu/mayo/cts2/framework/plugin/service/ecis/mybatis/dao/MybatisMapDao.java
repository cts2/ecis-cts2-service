package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.mayo.cts2.framework.model.mapversion.MapEntry;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;

/*
 * Method names will correspond to 'id's in the MyBatis mapping
 * files. 
 * 
 * See 'codeSystemMapper.xml'
 */
public interface MybatisMapDao {

	public List<MapEntry> getMapEntries(
			@Param("mapGuid") String mapGuid,
			@Param("limitOffset")  LimitOffset limtOffset);

}
