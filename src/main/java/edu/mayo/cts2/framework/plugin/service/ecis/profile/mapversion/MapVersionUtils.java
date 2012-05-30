package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapversion;

import org.apache.commons.lang.StringUtils;

import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;

public class MapVersionUtils extends AbstractService {
	
	private final static String VERSION_SUFFIX = "-v";

	public static String stripOffVersion(String mapVersionName) {
		int end = StringUtils.lastIndexOf(mapVersionName, VERSION_SUFFIX);
		return StringUtils.substring(mapVersionName, 0, end);
	}

}
