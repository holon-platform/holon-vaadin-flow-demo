package com.holonplatform.vaadin.flow.demo.services;

import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface CustomerService {

	Optional<PropertyBox> getCustomer(Long customerId);

	Optional<String> getFullname(Long customerId);

	OperationResult save(PropertyBox pbCustomer);

}
