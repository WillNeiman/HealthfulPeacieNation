package service;

import java.util.List;

import dto.OrderGoodsDTO;

public interface OrderGoodsService {

	public OrderGoodsDTO getOrderGoodsByOrderPk(Long orderPk);
	
	public List<OrderGoodsDTO> getOrderGoodsByUserId(String userId);
	
	public List<OrderGoodsDTO> getOrderGoodsByOrderId(Long orderId);
	
	public void updateReviewWritten(Long orderPk);
	
	public void updateRating(Long orderPk, int rating);
}
