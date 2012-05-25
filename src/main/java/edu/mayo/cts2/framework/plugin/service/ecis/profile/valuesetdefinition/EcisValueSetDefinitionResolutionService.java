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
package edu.mayo.cts2.framework.plugin.service.ecis.profile.valuesetdefinition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import edu.mayo.cts2.framework.model.command.Page;
import edu.mayo.cts2.framework.model.command.ResolvedReadContext;
import edu.mayo.cts2.framework.model.core.EntitySynopsis;
import edu.mayo.cts2.framework.model.core.MatchAlgorithmReference;
import edu.mayo.cts2.framework.model.core.PredicateReference;
import edu.mayo.cts2.framework.model.core.PropertyReference;
import edu.mayo.cts2.framework.model.core.SortCriteria;
import edu.mayo.cts2.framework.model.entity.EntityDirectoryEntry;
import edu.mayo.cts2.framework.model.service.core.NameOrURI;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSet;
import edu.mayo.cts2.framework.model.valuesetdefinition.ResolvedValueSetHeader;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.dao.MybatisResolvedValueSetDao;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.LimitOffset;
import edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination.PaginationUtils;
import edu.mayo.cts2.framework.plugin.service.ecis.profile.AbstractService;
import edu.mayo.cts2.framework.service.meta.StandardMatchAlgorithmReference;
import edu.mayo.cts2.framework.service.meta.StandardModelAttributeReference;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResolutionEntityQuery;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.ResolvedValueSetResult;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.ValueSetDefinitionResolutionService;
import edu.mayo.cts2.framework.service.profile.valuesetdefinition.name.ValueSetDefinitionReadId;

/**
 * The Class BioportalRdfResolvedValueSetResolutionService.
 *
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
@Component
public class EcisValueSetDefinitionResolutionService extends AbstractService 
	implements ValueSetDefinitionResolutionService {

	@Resource
	private MybatisResolvedValueSetDao resolvedValueSetDao;
	
	@Resource
	private TransitiveBuilder transitiveBuilder;

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

	@Override
	public ResolvedValueSetResult<EntitySynopsis> resolveDefinition(
			ValueSetDefinitionReadId definitionId,
			Set<NameOrURI> codeSystemVersions, 
			NameOrURI tag,
			ResolvedValueSetResolutionEntityQuery query,
			SortCriteria sortCriteria, 
			ResolvedReadContext readContext,
			Page page) {

		String valueSetName = definitionId.getValueSet().getName();
		
		LimitOffset limitOffset = PaginationUtils.getLimitOffset(page);
		
		if(query != null && CollectionUtils.isNotEmpty(query.getFilterComponent())) {	
			throw new UnsupportedOperationException("Filters not yet implemented on ResolvedValueSets.");
		} else {
			Set<String> codes = this.transitiveBuilder.getTransitiveCodes(valueSetName);
			
			if(CollectionUtils.isEmpty(codes)){
				return null;
			}
			
			List<EntitySynopsis> results = 
				resolvedValueSetDao.getResolvedValueSetSynopsis(codes, limitOffset);
			
			if(CollectionUtils.isEmpty(results)){
				return null;
			} else {
				return new ResolvedValueSetResult<EntitySynopsis>(
						this.getResolvedValueSetHeader(valueSetName), 
						results, 
						PaginationUtils.setAtEnd(results, limitOffset));
			}
		}
	}

	@Override
	public ResolvedValueSetResult<EntityDirectoryEntry> resolveDefinitionAsEntityDirectory(
			ValueSetDefinitionReadId definitionId,
			Set<NameOrURI> codeSystemVersions, NameOrURI tag,
			ResolvedValueSetResolutionEntityQuery query,
			SortCriteria sortCriteria, ResolvedReadContext readContext,
			Page page) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResolvedValueSet resolveDefinitionAsCompleteSet(
			ValueSetDefinitionReadId definitionId,
			Set<NameOrURI> codeSystemVersions, NameOrURI tag,
			ResolvedReadContext readContext) {
		throw new UnsupportedOperationException();
	}
	
	protected ResolvedValueSetHeader getResolvedValueSetHeader(String valueSetName){
		return this.resolvedValueSetDao.getResolvedValueSetHeader(valueSetName);
	}


	@Override
	public Set<? extends PropertyReference> getSupportedSortReferences() {
		return null;
	}

	@Override
	public Set<PredicateReference> getKnownProperties() {
		return null;
	}
}
