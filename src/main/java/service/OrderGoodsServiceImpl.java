package service;

import java.util.List;

import dto.OrderGoodsDTO;
import repository.OrderGoodsRepository;
import repository.OrderGoodsRepositoryImpl;

public class OrderGoodsServiceImpl implements OrderGoodsService{
	
	OrderGoodsRepository orderGoodsRepository = new OrderGoodsRepositoryImpl();

	@Override
	public OrderGoodsDTO getOrderGoodsByOrderPk(Long orderPk) {
	    System.out.println("OrderGoodsServiceImpl - getOrderGoodsByOrderPk");
		OrderGoodsDTO orderGoods = orderGoodsRepository.findByOrderPk(orderPk);
		return orderGoods;
	}

	@Override
	public List<OrderGoodsDTO> getOrderGoodsByUserId(String userId) {
	    System.out.println("OrderGoodsServiceImpl - getOrderGoodsByUserId");
		List<OrderGoodsDTO> orderGoodsList = orderGoodsRepository.findByUserId(userId);
		return orderGoodsList;
	}
	
	@Override
    public List<OrderGoodsDTO> getOrderGoodsByOrderId(Long orderId) {
	    System.out.println("OrderGoodsServiceImpl - getOrderGoodsByOrderId");
        List<OrderGoodsDTO> orderGoodsList = orderGoodsRepository.findByOrderId(orderId);
        return orderGoodsList;
    }

	@Override
	public void updateReviewWritten(Long orderPk) {
	    System.out.println("OrderGoodsServiceImpl - updateReviewWritten");
		orderGoodsRepository.updateReviewWritten(orderPk);
	}

	@Override
	public void updateRating(Long orderPk, int rating) {
	    System.out.println("OrderGoodsServiceImpl - updateReviewWritten");
	    orderGoodsRepository.updateRating(orderPk, rating);
	}
}

