package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.OrderDTO;
import dto.OrderGoodsDTO;
import dto.UserDTO;
import util.DbUtil;

public class OrderRepositoryImpl implements OrderRepository {
	Connection conn = null;

	@Override
	public void save(OrderDTO order) {
		System.out.println("OrderRepositoryImpl - save");
		String sql = "INSERT INTO orders (order_id, user_id, total_price, payment_method, order_status) VALUES (?, ?, ?, ?, '二쇰Ц����')";
		PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, order.getOrderId());
			pstmt.setString(2, order.getUserId());
			pstmt.setInt(3, order.getTotalPrice());
			pstmt.setString(4, order.getPaymentMethod());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
	}

	@Override
	public OrderDTO findById(Long orderId) {
		System.out.println("OrderRepositoryImpl - findById");
		OrderDTO order = null;
		String sql = "SELECT * FROM orders WHERE order_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				order = new OrderDTO();
				order.setOrderId(rs.getLong("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
			}
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return order;
	}

	@Override
	public List<OrderDTO> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> findAll() {
		System.out.println("OrderRepositoryImpl - findAll");
		List<OrderDTO> orders = new ArrayList<>();
		String sql = "SELECT * FROM orders ORDER BY order_id DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDTO order = new OrderDTO();
				order.setOrderId(rs.getLong("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
				orders.add(order);
			}
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
		return orders;
	}

	@Override
	public void updateOrderStatus(Long orderId, String orderStatus) {
		System.out.println("OrderRepositoryImpl - updateOrderStatus");
		String sql = "UPDATE orders SET order_status=? WHERE order_id=?";
		PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderStatus);
			pstmt.setLong(2, orderId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DbUtil.close(conn);
		}
	}

	// 珥� 二쇰Ц �� 援ы��湲�
	@Override
	public int count() {
		System.out.println("OrderRepositoryImpl - count");
		int count = 0;
		String sql = "SELECT COUNT(*) FROM orders";
		Statement statement = null;
		ResultSet rs = null;
		try {
			this.conn = DbUtil.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		System.out.println("return: " + count);
		return count;
	}

	// �뱀�� �レ�� �⑥��濡� �����댁�� List�� �댁�� ���ы��湲�
	@Override
	public List<OrderDTO> getListByRange(int start, int end) {
		System.out.println("OrderRepositoryImpl - getListByRange");
		List<OrderDTO> orders = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM orders ORDER BY order_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDTO order = new OrderDTO();
				order.setOrderId(rs.getLong("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return orders;
	}

	@Override
	public void delete(Long orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countByUserId(String userId) {
		System.out.println("OrderRepositoryImpl - countByUserId");
	    int count = 0;
	    String sql = "SELECT COUNT(*) AS count FROM orders WHERE user_id LIKE ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + userId + "%");
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt("count");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	    return count;
	}

	@Override
	public List<OrderDTO> findByUserIdAndRange(String userId, int start, int end) {
		System.out.println("OrderRepositoryImpl - findByUserIdAndRange");
	    List<OrderDTO> orders = new ArrayList<>();
	    String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM orders WHERE user_id LIKE ? ORDER BY order_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + userId + "%");
	        pstmt.setInt(2, start);
	        pstmt.setInt(3, end);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
				OrderDTO order = new OrderDTO();
				order.setOrderId(rs.getLong("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setOrderCreatedAt(rs.getTimestamp("order_created_at"));
				orders.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	    return orders;
	}

	@Override
	public List<OrderDTO> findByUserIdAndKeyword(String userId, String keyword, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDTO> findByKeyword(String keyword, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

}
