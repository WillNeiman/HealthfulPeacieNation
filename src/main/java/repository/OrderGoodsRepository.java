package repository;

import java.util.List;

import dto.OrderGoodsDTO;

public interface OrderGoodsRepository {
	
	public Long getNextOrderId();
	
	void save(OrderGoodsDTO order);
	
	public OrderGoodsDTO findByOrderPk(Long orderPk);
	
	public List<OrderGoodsDTO> findByOrderId(Long orderId);
	
	public List<OrderGoodsDTO> findByUserId(String userId);
	
	public List<OrderGoodsDTO> findAll();
	
	public void updateOrderStatus(Long orderId, String orderStatus);
	
	public void delete(Long orderId);
	
	public int countByUserId(String userId);
	
	public List<OrderGoodsDTO> findByUserIdAndRange(String userId, int start, int end);
	
	public List<OrderGoodsDTO> findByUserIdAndKeyword(String userId, String keyword, int start, int end);
	
	public int countByKeyword(String keyword);
	
	public List<OrderGoodsDTO> findByKeyword(String keyword, int start, int end);
	
	public void updateReviewWritten(Long orderPk);
	
	public void updateRating(Long orderPk, int rating);
	
	public List<OrderGoodsDTO> findByReviewedAndRated(Long goodsId);

}
