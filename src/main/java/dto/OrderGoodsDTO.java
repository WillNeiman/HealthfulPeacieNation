package dto;

import java.sql.Timestamp;

public class OrderGoodsDTO {
	
	private long orderPk;
	
	private long orderId;
	
	private String userId;
	
	private long goodsId;
	
	private int orderPrice;
	
	private int orderQuantity;
	
	private Timestamp orderCreatedAt;
	
	private String reviewWritten;
	
	private int rating;

	public OrderGoodsDTO(long orderPk, long orderId, String userId, long goodsId, int orderPrice, int orderQuantity,
			Timestamp orderCreatedAt) {
		this.orderPk = orderPk;
		this.orderId = orderId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.orderPrice = orderPrice;
		this.orderQuantity = orderQuantity;
		this.orderCreatedAt = orderCreatedAt;
	}

	public OrderGoodsDTO(long orderId, String userId, long goodsId, int orderPrice, int orderQuantity,
			Timestamp orderCreatedAt) {
		this.orderId = orderId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.orderPrice = orderPrice;
		this.orderQuantity = orderQuantity;
		this.orderCreatedAt = orderCreatedAt;
	}

	public OrderGoodsDTO(String userId) {
		// TODO Auto-generated constructor stub
	}

	public OrderGoodsDTO(String userId, long goodsId, int cartQuantity, int orderPrice) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.orderQuantity = cartQuantity;
		this.orderPrice = orderPrice;
		
	}

	public OrderGoodsDTO(Long orderId, String userId, long goodsId, int quantity, int orderPrice) {
		this.orderId = orderId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.orderQuantity = quantity;
		this.orderPrice = orderPrice;
	}

	public OrderGoodsDTO() {
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

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Timestamp getOrderCreatedAt() {
		return orderCreatedAt;
	}

	public void setOrderCreatedAt(Timestamp orderCreatedAt) {
		this.orderCreatedAt = orderCreatedAt;
	}

	public long getOrderPk() {
		return orderPk;
	}

	public void setOrderPk(long orderPk) {
		this.orderPk = orderPk;
	}

	public String getReviewWritten() {
		return reviewWritten;
	}

	public void setReviewWritten(String reviewWritten) {
		this.reviewWritten = reviewWritten;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
}