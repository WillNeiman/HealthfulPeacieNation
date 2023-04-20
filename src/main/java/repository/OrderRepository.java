package repository;

import java.util.List;

import dto.OrderDTO;
import dto.OrderGoodsDTO;
import dto.UserDTO;

public interface OrderRepository {
	
	public void save(OrderDTO order);
	
	public OrderDTO findById(Long orderId);
	
	public List<OrderDTO> findByUserId(String userId);
	
	public List<OrderDTO> findAll();
	
	public void updateOrderStatus(Long orderId, String orderStatus);
	
	public int count();
	
	public List<OrderDTO> getListByRange(int start, int end);
	
	public void delete(Long orderId);
	
	public int countByUserId(String userId);
	
	public List<OrderDTO> findByUserIdAndRange(String userId, int start, int end);
	
	public List<OrderDTO> findByUserIdAndKeyword(String userId, String keyword, int start, int end);
	
	public int countByKeyword(String keyword);
	
	public List<OrderDTO> findByKeyword(String keyword, int start, int end);

}
