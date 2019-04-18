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
