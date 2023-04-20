package service;

import java.util.List;

import dto.CartDTO;
import repository.CartRepository;
import repository.CartRepositoryImpl;

public class CartServiceImpl implements CartService{

    private CartRepository cartRepository = new CartRepositoryImpl();

    public CartServiceImpl() {
    }
    
	@Override
	public void addGoodsToCart(String userId, Long goodsId, int cartQuantity) {
		System.out.println("CartServiceImpl - addGoodsToCart");
		CartDTO cart = new CartDTO(userId, goodsId, cartQuantity);
        cartRepository.save(cart);
	}

	@Override
	public int findSameGoodsInCart(Long goodsId, String userId) {
		System.out.println("CartServiceImpl - findSameGoodsInCart");
		CartDTO cart = new CartDTO(goodsId, userId);
		return cartRepository.checkExistence(cart);
	}

	
	@Override
	public List<CartDTO> getCartList(String userId) {
		System.out.println("CartServiceImpl - getCartList");
	    return cartRepository.findAllById(userId);
	}

	@Override
	public void updateCartQuantity(Long cartId, String userId, int quantity) {
		System.out.println("CartServiceImpl - updateCartQuantity");
	    CartDTO cart = new CartDTO(cartId, userId, quantity);
	    cartRepository.updateQuantity(cart);
	}

	@Override
	public void removeAllFromCart(String userId) {
		System.out.println("CartServiceImpl - removeAllFromCart");
	    cartRepository.deleteAll(userId);
	}

	@Override
	public void removeGoodsFromCart(Long cartId) {
		System.out.println("CartServiceImpl - removeGoodsFromCart");
	    cartRepository.deleteByGoodsId(cartId);
	}

}
