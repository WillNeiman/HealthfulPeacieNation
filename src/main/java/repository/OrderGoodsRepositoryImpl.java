package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrderGoodsDTO;
import util.DbUtil;

public class OrderGoodsRepositoryImpl implements OrderGoodsRepository {
	Connection conn = null;
	
	public Long getNextOrderId() {
	    System.out.println("OrderGoodsRepositoryImpl - getNextOrderId");
	    String sql = "SELECT MAX(order_id) FROM orders";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getLong(1) + 1;
	        } else {
	            return 1L;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void save(OrderGoodsDTO order) {
		System.out.println("OrderGoodsRepositoryImpl - save");
		String sql = "INSERT INTO order_goods (order_id, user_id, goods_id, order_quantity, order_price) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setLong(1, order.getOrderId());
	        pstmt.setString(2, order.getUserId());
	        pstmt.setLong(3, order.getGoodsId());
	        pstmt.setInt(4, order.getOrderQuantity());
	        pstmt.setInt(5, order.getOrderPrice());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public OrderGoodsDTO findByOrderPk(Long orderPk) {
	    System.out.println("OrderGoodsRepositoryImpl - findByOrderPk");
	    OrderGoodsDTO orderGoods = new OrderGoodsDTO();
	    String sql = "SELECT * FROM order_goods WHERE order_pk = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, orderPk);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	orderGoods.setOrderPk(orderPk);
	        	orderGoods.setUserId(rs.getString("user_id"));
	        	orderGoods.setGoodsId(rs.getLong("goods_id"));
	        	orderGoods.setOrderQuantity(rs.getInt("order_quantity"));
	        	orderGoods.setOrderPrice(rs.getInt("order_price"));
	        	orderGoods.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
	        	orderGoods.setReviewWritten(rs.getString("review_written"));
	        	orderGoods.setRating(rs.getInt("rating"));
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	    return orderGoods;
	}
	
	@Override
	public List<OrderGoodsDTO> findByOrderId(Long orderId) {
	    System.out.println("OrderGoodsRepositoryImpl - findByOrderId");
	    List<OrderGoodsDTO> orderGoodsList = new ArrayList<>();
	    String sql = "SELECT * FROM order_goods WHERE order_id = ? ORDER BY order_created_at DESC";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, orderId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	OrderGoodsDTO orderGoods = new OrderGoodsDTO();
	        	orderGoods.setOrderPk(rs.getLong("order_pk"));
	        	orderGoods.setOrderId(rs.getLong("order_id"));
	        	orderGoods.setGoodsId(rs.getLong("goods_id"));
	        	orderGoods.setOrderQuantity(rs.getInt("order_quantity"));
	        	orderGoods.setOrderPrice(rs.getInt("order_price"));
	        	orderGoods.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
	        	orderGoods.setReviewWritten(rs.getString("review_written"));
	        	orderGoods.setRating(rs.getInt("rating"));
	        	orderGoodsList.add(orderGoods);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	    return orderGoodsList;
	}

	@Override
	public List<OrderGoodsDTO> findByUserId(String userId) {
	    System.out.println("OrderGoodsRepositoryImpl - findByUserId");
	    List<OrderGoodsDTO> orderGoodsList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM order_goods WHERE user_id = ? ORDER BY order_created_at DESC";
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	OrderGoodsDTO orderGoods = new OrderGoodsDTO();
	        	orderGoods.setOrderPk(rs.getLong("order_pk"));
	        	orderGoods.setOrderId(rs.getLong("order_id"));
	        	orderGoods.setGoodsId(rs.getLong("goods_id"));
	        	orderGoods.setOrderQuantity(rs.getInt("order_quantity"));
	        	orderGoods.setOrderPrice(rs.getInt("order_price"));
	        	orderGoods.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
	        	orderGoods.setReviewWritten(rs.getString("review_written"));
	        	orderGoods.setRating(rs.getInt("rating"));
	        	orderGoodsList.add(orderGoods);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	    return orderGoodsList;
	}

	@Override
	public List<OrderGoodsDTO> findAll() {
	    System.out.println("OrderGoodsRepositoryImpl - findAll");
	    List<OrderGoodsDTO> orders = new ArrayList<>();
	    String sql = "SELECT * FROM order_goods ORDER BY order_created_at DESC";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	OrderGoodsDTO orderGoods = new OrderGoodsDTO();
	        	orderGoods.setOrderPk(rs.getLong("order_pk"));
	        	orderGoods.setOrderId(rs.getLong("order_id"));
	        	orderGoods.setGoodsId(rs.getLong("goods_id"));
	        	orderGoods.setOrderQuantity(rs.getInt("order_quantity"));
	        	orderGoods.setOrderPrice(rs.getInt("order_price"));
	        	orderGoods.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
	        	orderGoods.setReviewWritten(rs.getString("review_written"));
	        	orderGoods.setRating(rs.getInt("rating"));
	            orders.add(orderGoods);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);            
		}
	    return orders;
	}

	@Override
	public void updateOrderStatus(Long orderId, String orderStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countByUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderGoodsDTO> findByUserIdAndRange(String userId, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderGoodsDTO> findByUserIdAndKeyword(String userId, String keyword, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderGoodsDTO> findByKeyword(String keyword, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateReviewWritten(Long orderPk) {
		System.out.println("OrderGoodsRepositoryImpl - updateReviewWritten");
	    String sql = "UPDATE order_goods SET review_written = ? WHERE order_pk = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "Y");
	        pstmt.setLong(2, orderPk);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void updateRating(Long orderPk, int rating) {
		System.out.println("OrderGoodsRepositoryImpl - updateRating");
	    String sql = "UPDATE order_goods SET rating = ? WHERE order_pk = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, rating);
	        pstmt.setLong(2, orderPk);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}		
	}
	
	@Override
	public List<OrderGoodsDTO> findByReviewedAndRated(Long goodsId) {
	    System.out.println("OrderGoodsRepositoryImpl - findByReviewedAndRated");
	    List<OrderGoodsDTO> orderGoodsList = new ArrayList<>();
	    String sql = "SELECT * FROM order_goods WHERE goods_id = ? AND review_written = 'Y' AND rating != 0";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, goodsId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            OrderGoodsDTO orderGoods = new OrderGoodsDTO();
	            orderGoods.setOrderPk(rs.getLong("order_pk"));
	            orderGoods.setOrderId(rs.getLong("order_id"));
	            orderGoods.setUserId(rs.getString("user_id"));
	            orderGoods.setGoodsId(rs.getLong("goods_id"));
	            orderGoods.setOrderQuantity(rs.getInt("order_quantity"));
	            orderGoods.setOrderPrice(rs.getInt("order_price"));
	            orderGoods.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
	            orderGoods.setReviewWritten(rs.getString("review_written"));
	            orderGoods.setRating(rs.getInt("rating"));
	            orderGoodsList.add(orderGoods);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
            DbUtil.close(conn);
		}
	    return orderGoodsList;
	}
}
