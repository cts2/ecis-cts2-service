/*
 * Copyright: (c) 2004-2012 Mayo Foundation for Medical Education and 
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
package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.pagination;

import java.util.List;

import org.springframework.util.Assert;

import edu.mayo.cts2.framework.model.command.Page;

/**
 * The Class PaginationUtils.
 *
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class PaginationUtils {

	/**
	 * Instantiates a new pagination utils.
	 */
	private PaginationUtils(){
		super();
	}
	
	/**
	 * Gets the limit given a page of results. NOTE that this will automatically
	 * increment LIMIT by one.
	 *
	 * @param page the page
	 * @return the limit offset
	 */
	public static LimitOffset getLimitOffset(Page page){
		if(page == null){
			page = new Page();
		} 
		
		return new LimitOffset(page);
	}
	
	/**
	 * Determines whether or not the database has reached the end of the result set.
	 * This assumes that the database has been asked for (results + 1) results. The 
	 * extra result is removed from the list, so callers should pass in the actual 
	 * result list reference, not a clone.
	 *
	 * @param entries the entries
	 * @param limitOffset the limit offset
	 * @return true, if at the end
	 */
	public static boolean setAtEnd(
			List<?> entries,
			LimitOffset limitOffset){
		Assert.notNull(limitOffset);
		Assert.notNull(entries);
		
		boolean atEnd = entries.size() < limitOffset.getLimit();
		
		if(! atEnd){
			entries.remove(entries.size() - 1);
		}

		return atEnd;
	}
}
