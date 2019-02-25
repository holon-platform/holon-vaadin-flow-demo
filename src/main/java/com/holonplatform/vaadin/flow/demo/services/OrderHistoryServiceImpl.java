/*
 * Copyright 2016-2018 Axioma srl.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.Query;
import com.holonplatform.core.query.QuerySort;
import com.holonplatform.vaadin.flow.demo.models.OrderHistory;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	private Datastore datastore;

	@Override
	public List<PropertyBox> getOrderHistory(Long orderId, QuerySort sort) {
		ObjectUtils.argumentNotNull(orderId, "Missing order Id");
		Query query = datastore.query(OrderHistory.TARGET).filter(OrderHistory.ORDER_ID.eq(orderId));
		if (sort != null) {
			query.sort(sort);
		}
		return query.list(OrderHistory.ORDER_HISTORY);
	}

	@Override
	public OperationResult save(PropertyBox pbOrderHistory) {
		ObjectUtils.argumentNotNull(pbOrderHistory, "Missing order history PropertyBox");
		return datastore.save(OrderHistory.TARGET, pbOrderHistory, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
