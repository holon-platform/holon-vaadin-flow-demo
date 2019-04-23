package com.holonplatform.vaadin.flow.demo.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.holonplatform.vaadin.flow.demo.enums.OrderState;

@Entity(name = "orders")
@Table(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "duedate")
	private LocalDate duedate;

	@Column(name = "duetime")
	private LocalTime duetime;

	@Column(name = "paid")
	private Integer paid;

	@Column(name = "customer")
	private Long customer;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private OrderState state;

	@Column(name = "pickup_location")
	private Integer pickup_location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

	public LocalTime getDuetime() {
		return duetime;
	}

	public void setDuetime(LocalTime duetime) {
		this.duetime = duetime;
	}

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Integer getPickup_location() {
		return pickup_location;
	}

	public void setPickup_location(Integer pickup_location) {
		this.pickup_location = pickup_location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
