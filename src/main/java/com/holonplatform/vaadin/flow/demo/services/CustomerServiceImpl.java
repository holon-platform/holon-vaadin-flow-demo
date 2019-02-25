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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.models.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<PropertyBox> getCustomer(Long customerId) {
		ObjectUtils.argumentNotNull(customerId, "Missing customer Id");
		return datastore.query(Customer.TARGET).filter(Customer.ID.eq(customerId)).findOne(Customer.CUSTOMER);
	}

	@Override
	public Optional<String> getFullname(Long customerId) {
		ObjectUtils.argumentNotNull(customerId, "Missing customer Id");
		return datastore.query(Customer.TARGET).filter(Customer.ID.eq(customerId)).findOne(Customer.FULLNAME);
	}

	@Override
	public OperationResult save(PropertyBox pbCustomer) {
		ObjectUtils.argumentNotNull(pbCustomer, "Missing customer PropertyBox");
		return datastore.save(Customer.TARGET, pbCustomer, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
