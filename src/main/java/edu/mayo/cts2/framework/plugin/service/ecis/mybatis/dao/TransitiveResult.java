package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TransitiveResult {

	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	
	public Set<String> getCodes(){
		return new HashSet<String>(
			Arrays.asList(
					this.level1,
					this.level2, 
					this.level3, 
					this.level4,
					this.level5));
	}
	
}
