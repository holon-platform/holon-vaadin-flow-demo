package com.holonplatform.vaadin.flow.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "sequence")
	private Long sequence;

	@Column(name = "orderid")
	private Long orderid;

	public Long getSequence() {
		return sequence;
	}

	public Long getOrderid() {
		return orderid;
	}

	public OrderItemId() {
		super();
	}

	public OrderItemId(Long sequence, Long orderid) {
		super();
		this.sequence = sequence;
		this.orderid = orderid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
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
		OrderItemId other = (OrderItemId) obj;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}

}
