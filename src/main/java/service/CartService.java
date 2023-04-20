package service;

import java.util.List;

import dto.CartDTO;

public interface CartService {
	
	public void addGoodsToCart(String userId, Long goodsId, int quantity);
	
	public int findSameGoodsInCart(Long goodsId, String userId);
	
	public List<CartDTO> getCartList(String userId);
	
	public void updateCartQuantity(Long cartId, String userId, int quantity);
	
	public void removeAllFromCart(String userId);
	
	public void removeGoodsFromCart(Long cartId);

}
