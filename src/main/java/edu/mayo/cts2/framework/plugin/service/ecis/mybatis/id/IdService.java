package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.id;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisIdServiceDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisIdServiceDao.CodeSystemIdResult;

@Component
public class IdService implements InitializingBean {

	private Map<String,CodeSystemIdResult> cache = new HashMap<String,CodeSystemIdResult>();
	
	@Resource
	private MybatisIdServiceDao mybatisIdServiceDao;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.initCache();
	}
	
	protected void initCache() {
		for(CodeSystemIdResult result : this.mybatisIdServiceDao.getCodeSystemIdResults()){
			this.cache.put(
				this.stripUnderscoresAndSpaces(result.getName()), result);
		}
	}

	public String getCodeSystemGuidFromName(String name) {
		CodeSystemIdResult idResult =
			this.cache.get(this.stripUnderscoresAndSpaces(name));
		
		if(idResult != null){
			return idResult.getGuid();
		} else {
			return null;
		}
	}
	
	private String stripUnderscoresAndSpaces(String name) {
		return StringUtils.replaceEachRepeatedly(
				name, 
				new String[]{"_"," "}, 
				new String[]{"",""});
	}
}
