package dto;

import java.sql.Timestamp;

public class CartDTO {
	
	private long cartId;
	
	private String userId;
	
	private long goodsId;
	
	private int cartQuantity;
	
	private Timestamp cartCreatedAt;

	public CartDTO(long cartId, String userId, long goodsId, int cartQuantity, Timestamp cartCreatedAt) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.cartQuantity = cartQuantity;
		this.cartCreatedAt = cartCreatedAt;
	}

	public CartDTO(String userId, Long goodsId, int cartQuantity) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.cartQuantity = cartQuantity;
		}

	public CartDTO(String userId, Long goodsId, int cartQuantity, Timestamp cartCreatedAt) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.cartQuantity = cartQuantity;
		this.cartCreatedAt = cartCreatedAt;
	}

	public CartDTO(Long cartId, String userId, int cartQuantity) {
		this.cartId = cartId;
		this.userId = userId;
		this.cartQuantity = cartQuantity;
	}

	public CartDTO(Long goodsId, String userId) {
		this.goodsId = goodsId;
		this.userId = userId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
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

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public Timestamp getCartCreatedAt() {
		return cartCreatedAt;
	}

	public void setCartCreatedAt(Timestamp cartCreatedAt) {
		this.cartCreatedAt = cartCreatedAt;
	}
	
}
