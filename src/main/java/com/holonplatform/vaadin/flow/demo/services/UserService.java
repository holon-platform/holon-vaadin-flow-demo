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
