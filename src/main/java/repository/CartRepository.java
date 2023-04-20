package repository;

import java.util.List;

import dto.CartDTO;

public interface CartRepository {
	
	public void save(CartDTO cart);
	
	public int checkExistence(CartDTO cartInfo);
	
	public List<CartDTO> findAllById(String userId);
	
	public void updateQuantity(CartDTO cart);
	
	public void deleteAll(String userId);
	
	public void deleteByGoodsId(Long cartId);
}
