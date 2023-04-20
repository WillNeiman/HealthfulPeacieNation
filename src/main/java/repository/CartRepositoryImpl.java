package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.CartDTO;
import util.DbUtil;

public class CartRepositoryImpl implements CartRepository{

	Connection conn = null;
	
	@Override
	public void save(CartDTO cart) {
		System.out.println("CartRepositoryImpl - save");
		String sql = "INSERT INTO carts (user_id, goods_id, cart_quantity) VALUES (?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getUserId());
	        pstmt.setLong(2, cart.getGoodsId());
	        pstmt.setInt(3, cart.getCartQuantity());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	@Override
	public int checkExistence(CartDTO cartInfo) {
	    System.out.println("CartRepositoryImpl - checkExistence");
	    int result = 0;
	    String sql = "SELECT COUNT(*) FROM carts WHERE user_id = ? AND goods_id = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, cartInfo.getUserId());
	        pstmt.setLong(2, cartInfo.getGoodsId());
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            result = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	    return result;
	}

	@Override
	public List<CartDTO> findAllById(String userId) {
		System.out.println("CartRepositoryImpl - findAllById");
		List<CartDTO> cartList = new ArrayList<>();
		String sql = "SELECT * FROM carts WHERE user_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long cartId = rs.getLong("cart_id");
				Long goodsId = rs.getLong("goods_id");
				int cartQuantity = rs.getInt("cart_quantity");
				Timestamp cartCreatedAt = rs.getTimestamp("cart_created_at");
				CartDTO cart = new CartDTO(cartId, userId, goodsId, cartQuantity, cartCreatedAt);
				cartList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return cartList;
	}

	@Override
    public void updateQuantity(CartDTO cart) {
		System.out.println("CartRepositoryImpl - updateQuantity");
        String sql = "UPDATE carts SET cart_quantity = ? WHERE user_id = ? AND cart_id = ?";
        PreparedStatement pstmt = null;
        try {
			this.conn = DbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cart.getCartQuantity());
            pstmt.setString(2, cart.getUserId());
            pstmt.setLong(3, cart.getCartId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
    }

	@Override
	public void deleteAll(String userId) {
		System.out.println("CartRepositoryImpl - deleteAll");
	    String sql = "DELETE FROM carts WHERE user_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	@Override
	public void deleteByGoodsId(Long cartId) {
		System.out.println("CartRepositoryImpl - deleteByGoodsId");
	    String sql = "DELETE FROM carts WHERE cart_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, cartId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}
