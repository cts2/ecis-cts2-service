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

import edu.mayo.cts2.framework.model.command.Page;

/**
 * The Class LimitOffset.
 *
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class LimitOffset {

	private int limit;
	private int offset;
	
	/**
	 * Instantiates a new limit offset, while incrementing the LIMIT by one
	 * in order to quickly tell if there are more results left to page.
	 *
	 * @param limit the limit
	 * @param offset the offset
	 */
	protected LimitOffset(int limit, int offset) {
		super();
		this.limit = limit + 1;
		this.offset = offset;
	}

	/**
	 * Instantiates a new limit offset, while incrementing the LIMIT by one
	 * in order to quickly tell if there are more results left to page.
	 * 
	 * @param page the page
	 */
	protected LimitOffset(Page page) {
		this(page.getMaxToReturn(), page.getStart());
	}
	
	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	
	/**
	 * Sets the limit.
	 *
	 * @param limit the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	
	/**
	 * Sets the offset.
	 *
	 * @param offset the new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
