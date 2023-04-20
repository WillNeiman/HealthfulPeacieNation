package service;

import java.util.List;

import dto.CartDTO;
import dto.GoodsDTO;
import dto.OrderDTO;
import dto.OrderGoodsDTO;
import dto.UserDTO;
import repository.CartRepository;
import repository.CartRepositoryImpl;
import repository.GoodsRepository;
import repository.GoodsRepositoryImpl;
import repository.OrderGoodsRepository;
import repository.OrderGoodsRepositoryImpl;
import repository.OrderRepository;
import repository.OrderRepositoryImpl;

public class OrderServiceImpl implements OrderService{
	
	OrderGoodsRepository orderGoodsRepository;
	GoodsRepository goodsRepository;
	CartService cartService;
	OrderRepository orderRepository;
	
	public OrderServiceImpl() {
		this.orderGoodsRepository = new OrderGoodsRepositoryImpl();	
		this.goodsRepository = new GoodsRepositoryImpl();
		this.cartService = new CartServiceImpl();
		this.orderRepository = new OrderRepositoryImpl();
	}

	@Override
	public void addOrder(String userId, Long goodsId, int quantity, String paymentMethod) {
		System.out.println("OrderServiceImpl - addOrder");
	    Long orderId = orderGoodsRepository.getNextOrderId();
	    GoodsDTO goods = goodsRepository.findById(goodsId);
	    
	    int updatedStock = goods.getGoodsStock() - quantity;
	    int totalPrice = goods.getGoodsCost() * quantity;

        if (updatedStock < 0) {
            throw new RuntimeException("�ш� 遺�議깆�쇰� 二쇰Ц�� 遺�媛��ν�⑸����.");
        }

        int orderPrice = goods.getGoodsCost() * quantity;
        OrderGoodsDTO newOrder = new OrderGoodsDTO(orderId, userId, goods.getGoodsId(), quantity, orderPrice);
        orderGoodsRepository.save(newOrder);
        OrderDTO order = new OrderDTO(orderId, userId, totalPrice, paymentMethod);
        orderRepository.save(order);

        goodsRepository.updateGoodsStock(goods, -quantity);
        goodsRepository.updateGoodsSales(goods, quantity);
	}
	
	@Override
	public void orderAllGoods(String userId, String paymentMethod) {
		System.out.println("OrderServiceImpl - orderAllGoods");
	    List<CartDTO> cartList = cartService.getCartList(userId);
	    Long orderId = orderGoodsRepository.getNextOrderId();
	    int totalPrice = 0;
	    
	    for (CartDTO cart : cartList) {
	        GoodsDTO goods = goodsRepository.findById(cart.getGoodsId());
	        int updatedStock = goods.getGoodsStock() - cart.getCartQuantity();
		    totalPrice += goods.getGoodsCost() * cart.getCartQuantity();
	        if (updatedStock < 0) {
	            throw new RuntimeException("�ш� 遺�議깆�쇰� 二쇰Ц�� 遺�媛��ν�⑸����.");
	        }

	        int orderPrice = goods.getGoodsCost() * cart.getCartQuantity();

	        OrderGoodsDTO newOrder = new OrderGoodsDTO(orderId, userId, goods.getGoodsId(), cart.getCartQuantity(), orderPrice);
	        orderGoodsRepository.save(newOrder);

	        goodsRepository.updateGoodsStock(goods, cart.getCartQuantity());
	        goodsRepository.updateGoodsSales(goods, cart.getCartQuantity());
	        cartService.removeGoodsFromCart(cart.getCartId());
	    }

        OrderDTO order = new OrderDTO(orderId, userId, totalPrice, paymentMethod);
        orderRepository.save(order);
	}
	
	@Override
	public OrderDTO getOrder(Long orderId) {
	    OrderDTO order = orderRepository.findById(orderId);
	    return order;
	}

	@Override
	public List<OrderDTO> getOrdersByUserId(String userId) {
	    return orderRepository.findByUserId(userId);
	}

	@Override
	public List<OrderDTO> getAllOrders() {
	    System.out.println("OrderServiceImpl - getAllOrders");
	    List<OrderDTO> orders = orderRepository.findAll();
	    return orders;
	}

	@Override
	public void updateOrderStatus(Long orderId, String orderStatus) {
	    orderRepository.updateOrderStatus(orderId, orderStatus);
	}

	@Override
	public int getOrderCount() {
		return orderRepository.count();
	}
	
	@Override
	public List<OrderDTO> getOrderListByRange(int start, int end){
		return orderRepository.getListByRange(start, end);
	}

	@Override
	public void deleteOrder(Long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countOrdersByUserId(String userId) {
		return orderRepository.countByUserId(userId);
	}

	@Override
	public List<OrderDTO> getOrdersByUserIdAndRange(String userId, int start, int end) {
		return orderRepository.findByUserIdAndRange(userId, start, end);
	}

	@Override
	public List<OrderDTO> searchOrdersByUserIdAndKeyword(String userId, String keyword, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countOrdersByKeyword(String keyword) {
	    System.out.println("OrderServiceImpl - countOrdersByKeyword");
	    System.out.println("keyword: " + keyword);
		return orderRepository.countByKeyword(keyword);
	}

	@Override
	public List<OrderDTO> searchOrdersByKeyword(String keyword, int start, int end) {
	    System.out.println("OrderServiceImpl - searchOrdersByKeyword");
	    System.out.println("keyword: " + keyword);
		return orderRepository.findByUserIdAndRange(keyword, start, end);
	}
	

}
