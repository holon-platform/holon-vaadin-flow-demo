package com.holonplatform.vaadin.flow.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "orderitems")
@Table(name = "orderitems")
public class OrderItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemId orderItemId;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "product")
	private Integer product;

	@Column(name = "comment")
	private String comment;

	public OrderItemId getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(OrderItemId orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
