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
