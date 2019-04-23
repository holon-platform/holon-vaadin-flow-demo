package com.holonplatform.vaadin.flow.demo.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface ProductService {

	Optional<Double> getPrice(Integer productId);

	Optional<String> getName(Integer productId);

	List<PropertyBox> getProducts();

	OperationResult save(PropertyBox pbProduct);

	OperationResult delete(PropertyBox pbProduct);

}
