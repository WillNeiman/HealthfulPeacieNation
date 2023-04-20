package repository;

import java.sql.*;
import java.util.*;
import dto.*;
import util.DbUtil;

public class ReviewRepositoryImpl implements ReviewRepository {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public void save(ReviewDTO review) {
	    System.out.println("ReviewRepositoryImpl - save");
		try {
			this.conn = DbUtil.getConnection();
			String sql = "INSERT INTO reviews (review_id, goods_id, user_id, order_pk, review_content) VALUES (review_id_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, review.getGoodsId());
			pstmt.setString(2, review.getUserId());
			pstmt.setLong(3, review.getOrderPk());
			pstmt.setString(4, review.getReviewContent());
			pstmt.executeUpdate();
		    System.out.println(review.getUserId() + " 由щ럭 ���� ��猷�");			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
	}
	
	@Override
	public void update(ReviewDTO review) {
	    System.out.println("ReviewRepositoryImpl - update");
		try {
			this.conn = DbUtil.getConnection();
			String sql = "update reviews set review_content=? where review_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReviewContent());
			pstmt.setLong(2, review.getReviewId());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
	}
	
	@Override
	public void delete(long reviewId) {
	    System.out.println("ReviewRepositoryImpl - delete");
		try {
			this.conn = DbUtil.getConnection();
			String sql = "delete from reviews where review_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, reviewId);
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
	}
	
	@Override
	public ReviewDTO select(long reviewId) {
	    System.out.println("ReviewRepositoryImpl - select");
		ReviewDTO review = new ReviewDTO();
		
		try {
			this.conn = DbUtil.getConnection();
			String sql = "select * from reviews where review_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, reviewId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review.setReviewId(rs.getLong("review_id"));
				review.setGoodsId(rs.getLong("goods_id"));
				review.setUserId(rs.getString("user_id"));
				review.setOrderPk(rs.getLong("order_pk"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewCreatedAt(rs.getTimestamp("review_created_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return review;
	}
	
	
	@Override
	public List<ReviewDTO> findAllById(long goodsId) {
	    System.out.println("ReviewRepositoryImpl - findAllById");
		String sql = "select * from reviews where goods_id = ? ORDER BY review_created_at DESC";
		List<ReviewDTO> reviews = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, goodsId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO review = new ReviewDTO();
				review.setReviewId(rs.getLong("review_id"));
				review.setGoodsId(goodsId);
				review.setUserId(rs.getString("user_id"));
				review.setOrderPk(rs.getLong("order_pk"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewCreatedAt(rs.getTimestamp("review_created_at"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return reviews;
	}
	
	@Override
	public List<ReviewDTO> findAllById(String userId) {
	    System.out.println("ReviewRepositoryImpl - findAllById");
		String sql = "select * from reviews where user_id = ? ORDER BY review_created_at DESC";
		List<ReviewDTO> reviews = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO review = new ReviewDTO();
				review.setReviewId(rs.getLong("review_id"));
				review.setGoodsId(rs.getLong("goods_id"));
				review.setUserId(userId);
				review.setOrderPk(rs.getLong("order_pk"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewCreatedAt(rs.getTimestamp("review_created_at"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return reviews;
	}
	
	@Override
	public List<ReviewDTO> findAll() {
	    System.out.println("ReviewRepositoryImpl - findAll");
		String sql = "select * from reviews ORDER BY review_created_at DESC";
		List<ReviewDTO> reviews = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO review = new ReviewDTO();
				review.setReviewId(rs.getLong("review_id"));
				review.setGoodsId(rs.getLong("goods_id"));
				review.setUserId(rs.getString("user_id"));
				review.setOrderPk(rs.getLong("order_pk"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewCreatedAt(rs.getTimestamp("review_created_at"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return reviews;
	}
	
	@Override
	public List<ReviewDTO> findRecentThreeById(long goodsId) {
	    System.out.println("ReviewRepositoryImpl - findRecentThreeById");
	    String sql = "SELECT * FROM (SELECT * FROM reviews WHERE goods_id = ? ORDER BY review_created_at DESC) WHERE ROWNUM <= 3";
	    List<ReviewDTO> reviews = new ArrayList<>();
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, goodsId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            ReviewDTO review = new ReviewDTO();
	            System.out.println("rs�� 媛�泥대�� 媛��몄�ㅻ�� 以�");
	            review.setReviewId(rs.getLong("review_id"));
	            review.setGoodsId(goodsId);
	            review.setUserId(rs.getString("user_id"));
	            review.setOrderPk(rs.getLong("order_pk"));
	            review.setReviewContent(rs.getString("review_content"));
	            review.setReviewCreatedAt(rs.getTimestamp("review_created_at"));
	            reviews.add(review);
	            System.out.println("rs�� 媛�泥대�� 由ъ�ㅽ�몄�� ����");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
        System.out.println("由ъ�ㅽ�� 諛���");
	    return reviews;
	}
}
