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
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.demo.models.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<Double> getPrice(Integer productId) {
		ObjectUtils.argumentNotNull(productId, "Missing product Id");
		return datastore.query(Product.TARGET).filter(Product.ID.eq(productId)).findOne(Product.PRICE);
	}

	@Override
	public Optional<String> getName(Integer productId) {
		ObjectUtils.argumentNotNull(productId, "Missing product Id");
		return datastore.query(Product.TARGET).filter(Product.ID.eq(productId)).findOne(Product.NAME);
	}

	@Override
	public List<PropertyBox> getProducts() {
		return datastore.query(Product.TARGET).list(Product.PRODUCT);
	}

	@Override
	public OperationResult save(PropertyBox pbProduct) {
		ObjectUtils.argumentNotNull(pbProduct, "Missing product PropertyBox");
		return datastore.save(Product.TARGET, pbProduct, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(PropertyBox pbProduct) {
		ObjectUtils.argumentNotNull(pbProduct, "Missing product PropertyBox");
		return datastore.delete(Product.TARGET, pbProduct, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
