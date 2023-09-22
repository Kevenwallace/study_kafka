package br.com.ecommerce;

import java.math.BigDecimal;

public class Order {
	@SuppressWarnings("unused")
	private final String userId, orderId;
	@SuppressWarnings("unused")
	private final BigDecimal value;
	
	
	public Order(String userId, String orderId, BigDecimal value) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}
