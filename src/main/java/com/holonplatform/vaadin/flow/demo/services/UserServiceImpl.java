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
import com.holonplatform.vaadin.flow.demo.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<String> getUserName(Long userId) {
		ObjectUtils.argumentNotNull(userId, "Missing user ID");
		return datastore.query(User.TARGET).filter(User.ID.eq(userId)).findOne(User.NAME);
	}

	@Override
	public Optional<PropertyBox> getUserByEmail(String email) {
		ObjectUtils.argumentNotNull(email, "Missing user email");
		return datastore.query(User.TARGET).filter(User.EMAIL.eq(email)).findOne(User.USER);
	}

	@Override
	public OperationResult delete(PropertyBox pbUser) {
		ObjectUtils.argumentNotNull(pbUser, "Missing user Property Box");
		return datastore.delete(User.TARGET, pbUser, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult save(PropertyBox pbUser) {
		ObjectUtils.argumentNotNull(pbUser, "Missing user Property Box");
		return datastore.save(User.TARGET, pbUser, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

}
