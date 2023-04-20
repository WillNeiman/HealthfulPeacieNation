package dto;

import java.sql.Timestamp;

public class OrderDTO {
	
	private long orderId;
	
	private String userId;
	
	private int totalPrice;
	
	private String paymentMethod;
	
	private String orderStatus;
	
	private Timestamp orderCreatedAt;

	public OrderDTO(long orderId, String userId, int totalPrice, String paymentMethod, String orderStatus,
			Timestamp orderCreatedAt) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.orderCreatedAt = orderCreatedAt;
	}

	public OrderDTO(long orderId, String userId, int totalPrice, String paymentMethod) {
		this.orderId = orderId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		
	}

	public OrderDTO() {
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderCreatedAt() {
		return orderCreatedAt;
	}

	public void setOrderCreatedAt(Timestamp orderCreatedAt) {
		this.orderCreatedAt = orderCreatedAt;
	}
	
	

}
