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
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.models.OrderItem;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private Datastore datastore;

	@Override
	public List<PropertyBox> getOrderItems(Long orderId) {
		ObjectUtils.argumentNotNull(orderId, "Order Id cannot be null");
		return datastore.query(OrderItem.TARGET).filter(OrderItem.ORDER.eq(orderId)).list(OrderItem.ORDER_ITEM);
	}

	@Override
	public List<PropertyBox> getOrderItems(Long orderId, Property... properties) {
		ObjectUtils.argumentNotNull(orderId, "Order Id cannot be null");
		ObjectUtils.argumentNotNull(properties, "Properties to select cannot be null");
		return datastore.query(OrderItem.TARGET).filter(OrderItem.ORDER.eq(orderId)).list(properties);
	}

	@Override
	public Long orderItemsCount(Integer productId) {
		return datastore.query(OrderItem.TARGET).filter(OrderItem.PRODUCT.eq(productId)).count();
	}

	@Override
	public OperationResult save(PropertyBox pbOrderItem) {
		return datastore.save(OrderItem.TARGET, pbOrderItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(PropertyBox pbOrderItem) {
		return datastore.delete(OrderItem.TARGET, pbOrderItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
