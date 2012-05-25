package edu.mayo.cts2.framework.plugin.service.ecis.profile.valuesetdefinition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisResolvedValueSetDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.TransitiveResult;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.ValueSetDefinitionEntryInfo;

@Component
public class TransitiveBuilder {
	
	@Resource
	private MybatisResolvedValueSetDao resolvedValueSetDao;
	
	public Set<String> getTransitiveCodes(String valueSetName){
		Set<String> totalResults = new HashSet<String>();
		Map<String,Set<String>> predicateGuidCodeMap = new HashMap<String,Set<String>>();
		
		Set<ValueSetDefinitionEntryInfo> entries = this.resolvedValueSetDao.getEntries(valueSetName);
		
		for (ValueSetDefinitionEntryInfo entry : entries) {
			if(entry.isTransitive()){
				String predicateGuid = entry.getPredicateGuid();
				String entityCode = entry.getEntityCode();
				
				if(! predicateGuidCodeMap.containsKey(predicateGuid)){
					predicateGuidCodeMap.put(predicateGuid, new HashSet<String>());
				}
				
				predicateGuidCodeMap.get(predicateGuid).add(entityCode);
				
			} else {
				totalResults.addAll(this.getOneLevelCodes(entry));
			}
			
		}
		
		for(Entry<String, Set<String>> entry : predicateGuidCodeMap.entrySet()){
			EntityCodesAndPredicateGuid codesAndPredicate = new EntityCodesAndPredicateGuid();
			codesAndPredicate.predicateGuid = entry.getKey();
			codesAndPredicate.entityCodes = entry.getValue();
			
			totalResults.addAll(this.getTransitiveCodes(codesAndPredicate));
		}
		
		return totalResults;
	}
	
	private Set<String> getOneLevelCodes(ValueSetDefinitionEntryInfo entry) {
		return this.resolvedValueSetDao.resolveOneLevelRelations(
				new HashSet<String>(Arrays.asList(entry.getEntityCode())), 
				entry.getPredicateGuid());
	}
	
	private Set<String> getTransitiveCodes(EntityCodesAndPredicateGuid entry) {
		Set<String> totalResults = new HashSet<String>();
		Set<String> recurseFurther = new HashSet<String>();
		
		List<TransitiveResult> results = 
				this.resolvedValueSetDao.resolveTransitiveRelationsFiveLevels(
						entry.entityCodes, 
						entry.predicateGuid);
		
		for(TransitiveResult result : results){
			if(result.getLevel5() != null){
				recurseFurther.add(result.getLevel5());
			}
			
			totalResults.addAll(result.getCodes());
		}
		
		if(recurseFurther.size() > 0){
			EntityCodesAndPredicateGuid newEntry = new EntityCodesAndPredicateGuid();
			newEntry.entityCodes = recurseFurther;
			newEntry.predicateGuid = entry.predicateGuid;
			
			totalResults.addAll(this.getTransitiveCodes(newEntry));
		}
		
		return totalResults;
	}
	
	private static class EntityCodesAndPredicateGuid {
		private Set<String> entityCodes;
		private String predicateGuid;
	}
}
