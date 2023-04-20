package service;

import java.util.List;

import dto.CartDTO;
import dto.OrderDTO;
import dto.OrderGoodsDTO;

public interface OrderService {
	
	void addOrder(String userId, Long goodsId, int quantity, String paymentMethodr);
	
	public void orderAllGoods(String userId, String paymentMethod);
	
	public OrderDTO getOrder(Long orderId);
	
	List<OrderDTO> getOrdersByUserId(String userId);
	
	List<OrderDTO> getAllOrders();
	
	void updateOrderStatus(Long orderId, String orderStatus);
	
	int getOrderCount();
	
	public List<OrderDTO> getOrderListByRange(int start, int end);
	
	void deleteOrder(Long orderId);
	
	int countOrdersByUserId(String userId);
	
	List<OrderDTO> getOrdersByUserIdAndRange(String userId, int start, int end);
	
	List<OrderDTO> searchOrdersByUserIdAndKeyword(String userId, String keyword, int start, int end);
	
	int countOrdersByKeyword(String keyword);
	
	List<OrderDTO> searchOrdersByKeyword(String keyword, int start, int end);
	
	
}
