/*
 * Copyright: (c) 2004-2011 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.mayo.cts2.framework.plugin.service.ecis.profile.resolvedvalueset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.command.ResolvedFilter;
import edu.mayo.cts2.framework.model.core.EntitySynopsis;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.directory.DirectoryResult;
import edu.mayo.cts2.framework.model.entity.EntityDescription;
import edu.mayo.cts2.framework.model.entity.EntityDirectoryEntry;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSet;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisResolvedValueSetDao;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.meta.StandardMatchAlgorithmReference;
import edu.mayo.cts2.framework.service.meta.StandardModelAttributeReference;
import edu.mayo.cts2.framework.service.profile.resolvedvalueset.ResolvedValueSetResolutionService;
import edu.mayo.cts2.framework.service.profile.resolvedvalueset.name.ResolvedValueSetReadId;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResolutionEntityQuery;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResult;

/**
 * The Class BioportalRdfResolvedValueSetResolutionService.
 *
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
@Component
public class EcisResolvedValueSetResolutionService extends AbstractService 
	implements ResolvedValueSetResolutionService {
	@Resource
	MybatisResolvedValueSetDao resolvedValueSetDao;

	@Override
	public ResolvedValueSetResult getResolution(
			ResolvedValueSetReadId identifier,
			Set<ResolvedFilter> filterComponent, 
			Page page) {
		
		String id = identifier.getLocalName();
		String definition = identifier.getValueSet().getName();
		
		if(CollectionUtils.isEmpty(filterComponent)){			
			List<EntitySynopsis> results = resolvedValueSetDao.getResolvedValueSetSynopsis(definition);

			
			if(results == null){
				return null;
			}
			
			boolean moreResults = results.size() > page.getMaxToReturn();
			
			if(moreResults){
				results.remove(results.size() - 1);
			}
					
			return new ResolvedValueSetResult(
					this.getResolvedValueSetHeader(id), 
					results, 
					!moreResults);
		} 
		return null;
	}
	
	/* (non-Javadoc)
	 * @see edu.mayo.cts2.framework.service.profile.resolvedvalueset.ResolvedValueSetResolutionService#getEntities(edu.mayo.cts2.framework.service.profile.resolvedvalueset.name.ResolvedValueSetReadId, edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResolutionEntityQuery, edu.mayo.cts2.framework.model.core.SortCriteria, edu.mayo.cts2.framework.model.command.Page)
	 */
	@Override
	public ResolvedValueSetResult<EntityDirectoryEntry> getEntities(
			ResolvedValueSetReadId identifier,
			ResolvedValueSetResolutionEntityQuery query,
			SortCriteria sortCriteria, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.mayo.cts2.framework.service.profile.resolvedvalueset.ResolvedValueSetResolutionService#getEntityList(edu.mayo.cts2.framework.service.profile.resolvedvalueset.name.ResolvedValueSetReadId, edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResolutionEntityQuery, edu.mayo.cts2.framework.model.core.SortCriteria, edu.mayo.cts2.framework.model.command.Page)
	 */
	@Override
	public DirectoryResult<EntityDescription> getEntityList(
			ResolvedValueSetReadId identifier,
			ResolvedValueSetResolutionEntityQuery query,
			SortCriteria sortCriteria, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	protected ResolvedValueSetHeader getResolvedValueSetHeader(String id){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("id", id);
		
		return null;
	}

	@Override
	public ResolvedValueSet getResolution(
			ResolvedValueSetReadId identifier) {
		throw new UnsupportedOperationException("Cannot resolve the complete ResolvedValueSet yet...");
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSortReferences() {
		return null;
	}

	@Override
	public Set<PredicateReference> getKnownProperties() {
		return null;
	}

	@Override
	public Set<? extends MatchAlgorithmReference> getSupportedMatchAlgorithms() {
		HashSet<MatchAlgorithmReference> returnSet = new HashSet<MatchAlgorithmReference>();
		
		returnSet.add(StandardMatchAlgorithmReference.CONTAINS.getMatchAlgorithmReference());
		returnSet.add(StandardMatchAlgorithmReference.EXACT_MATCH.getMatchAlgorithmReference());

		return returnSet;
	}

	@Override
	public Set<? extends PropertyReference> getSupportedSearchReferences() {
		HashSet<PropertyReference> returnSet = new HashSet<PropertyReference>();

		returnSet.add(StandardModelAttributeReference.RESOURCE_SYNOPSIS.getPropertyReference());
		
		return returnSet;
	}

	
}
