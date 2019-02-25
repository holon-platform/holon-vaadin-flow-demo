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

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface UserService {

	Optional<String> getUserName(Long userId);

	Optional<PropertyBox> getUserByEmail(String email);

	OperationResult delete(PropertyBox pbUser);

	OperationResult save(PropertyBox pbUser);

}
