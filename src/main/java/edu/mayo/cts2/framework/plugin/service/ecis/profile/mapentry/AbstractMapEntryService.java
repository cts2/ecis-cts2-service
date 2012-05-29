package edu.mayo.cts2.framework.plugin.service.ecis.profile.mapentry;

import org.apache.commons.lang.StringUtils;

import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;

public class AbstractMapEntryService extends AbstractService {
	
	protected final static String VERSION_SUFFIX = "-v";

	protected String stripOffVersion(String mapVersionName) {
		int end = StringUtils.lastIndexOf(mapVersionName, VERSION_SUFFIX);
		return StringUtils.substring(mapVersionName, 0, end);
	}

}
