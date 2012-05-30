package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.io.Serializable;
import java.util.List;

/*
 * Method names will correspond to 'id's in the MyBatis mapping
 * files. 
 * 
 * See 'codeSystemMapper.xml'
 */
public interface MybatisIdServiceDao {

	public List<CodeSystemIdResult> getCodeSystemIdResults();
	
	public static class CodeSystemIdResult implements Serializable {
	
		private static final long serialVersionUID = 3914282027649631967L;
		
		private String uri;
		private String name;
		private String guid;
		
		public String getUri() {
			return uri;
		}
		
		public void setUri(String uri) {
			this.uri = uri;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		public String getGuid() {
			return guid;
		}
		
		public void setGuid(String guid) {
			this.guid = guid;
		}
	}

}
