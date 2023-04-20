package repository;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.GoodsDTO;
import util.DbUtil;

public class GoodsRepositoryImpl implements GoodsRepository {

	Connection conn = null;
	
	@Override
	public void save(GoodsDTO goods) {
		System.out.println("GoodsRepositoryImpl - save");
		String sql = "INSERT INTO goods (goods_id, goods_name, goods_cost, goods_stock, goods_thumbnail, goods_image1, goods_image2, goods_description, goods_category) VALUES (goods_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setInt(2, goods.getGoodsCost());
			pstmt.setInt(3, goods.getGoodsStock());
			pstmt.setString(4, goods.getThumbnail());
			if(goods.getGoodsImage1()!=null) {
			pstmt.setString(5, goods.getGoodsImage1());
			} else {
				pstmt.setString(5, "No-Image-Placeholder.png");
			}
			if(goods.getGoodsImage2()!=null) {
			pstmt.setString(6, goods.getGoodsImage2());
			} else {
				pstmt.setString(6, "No-Image-Placeholder.png");
			}
			pstmt.setString(7, goods.getGoodsDescription());
			pstmt.setString(8, goods.getGoodsCategory());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		
	}
	
	@Override
	public GoodsDTO findById(Long goodsId) {
		System.out.println("GoodsRepositoryImpl - findById");
		String sql = "SELECT * FROM goods WHERE goods_id = ?";
		GoodsDTO goods = new GoodsDTO();
		PreparedStatement pstmt = null;
		try {
			this.conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, goodsId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				goods.setGoodsId(goodsId);
				goods.setGoodsName(rs.getString("goods_name"));
				goods.setGoodsCost(rs.getInt("goods_cost"));
				goods.setGoodsStock(rs.getInt("goods_stock"));
				goods.setGoodsScore(rs.getDouble("goods_score"));
				goods.setThumbnail(rs.getString("goods_thumbnail"));
				goods.setGoodsImage1(rs.getString("goods_image1"));
				goods.setGoodsImage2(rs.getString("goods_image2"));
				Clob clob = rs.getClob("goods_description");
				String goodsDescription = clob != null ? clob.getSubString(1, (int) clob.length()) : null;
				goods.setGoodsDescription(goodsDescription);
				goods.setGoodsSales(rs.getInt("goods_sales"));
				goods.setGoodsCategory(rs.getString("goods_category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return goods;
	}

	@Override
	public List<GoodsDTO> findAll() {
		System.out.println("GoodsRepositoryImpl - findAll");
	    List<GoodsDTO> goodsList = new ArrayList<>();
	    String sql = "SELECT * FROM goods ORDER BY goods_id DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	    		GoodsDTO goods = new GoodsDTO();
				goods.setGoodsId(rs.getLong("goods_id"));
				goods.setGoodsName(rs.getString("goods_name"));
				goods.setGoodsCost(rs.getInt("goods_cost"));
				goods.setGoodsStock(rs.getInt("goods_stock"));
				goods.setGoodsScore(rs.getDouble("goods_score"));
				goods.setThumbnail(rs.getString("goods_thumbnail"));
				goods.setGoodsImage1(rs.getString("goods_image1"));
				goods.setGoodsImage2(rs.getString("goods_image2"));
				Clob clob = rs.getClob("goods_description");
				String goodsDescription = clob != null ? clob.getSubString(1, (int) clob.length()) : null;
				goods.setGoodsDescription(goodsDescription);
				goods.setGoodsSales(rs.getInt("goods_sales"));
				goods.setGoodsCategory(rs.getString("goods_category"));
	            goodsList.add(goods);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	    return goodsList;
	}

	//紐⑤�� ����瑜� �� ���곗�댄�� �댁�� ���� 寃쎌��
	@Override
	public void updateAllColumn(GoodsDTO goods) {
		System.out.println("GoodsRepositoryImpl - updateAllColumn");
	    String sql = "UPDATE goods SET goods_name=?, goods_cost=?, goods_stock=?, goods_score=?, goods_thumbnail=?, goods_image1=?, goods_image2=? WHERE goods_id=?";
		PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, goods.getGoodsName());
	        pstmt.setInt(2, goods.getGoodsCost());
	        pstmt.setInt(3, goods.getGoodsStock());
	        pstmt.setDouble(4, goods.getGoodsScore());
	        pstmt.setString(5, goods.getThumbnail());
	        pstmt.setString(6, goods.getGoodsImage1());
	        pstmt.setString(7, goods.getGoodsImage2());
	        pstmt.setLong(8, goods.getGoodsId());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void delete(Long goodsId) {
		System.out.println("GoodsRepositoryImpl - delete");
	    String sql = "DELETE FROM goods WHERE goods_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, goodsId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void updateGoodsStock(GoodsDTO goods, int quantity) {
		System.out.println("GoodsRepositoryImpl - updateGoodsStock");
		System.out.println("���� ID : " + goods.getGoodsId() + " ������ ���� : " + quantity);
	    String sql = "UPDATE goods SET goods_stock = goods_stock + ? WHERE goods_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, quantity);
	        pstmt.setLong(2, goods.getGoodsId());
	        pstmt.executeUpdate();
	        System.out.println("�ш� ���� ��猷�");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	}
	
	@Override
	public void updateGoodsSales(GoodsDTO goods, int quantity) {
		System.out.println("GoodsRepositoryImpl - updateGoodsSales");
		System.out.println("���� ID : " + goods.getGoodsId() + "Parameter : " + quantity);
	    String sql = "UPDATE goods SET goods_sales = goods_sales + ? WHERE goods_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, quantity);
	        pstmt.setLong(2, goods.getGoodsId());
	        pstmt.executeUpdate();
	        System.out.println("��留ㅻ�� ���� ��猷�");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void updateGoodsScore(Long goodsId, Double rating) {
		System.out.println("GoodsRepositoryImpl - updateGoodsSales");
	    String sql = "UPDATE goods SET goods_score = ? WHERE goods_id = ?";
	    PreparedStatement pstmt = null;
	    try {
			this.conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setDouble(1, rating);
	        pstmt.setLong(2, goodsId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}		
	}
	
	@Override
	   public void update(GoodsDTO goods) {
	      System.out.println("GoodsRepositoryImpl - updateAllColumn");
	       String sql = "UPDATE goods SET goods_name=?, goods_cost=?, goods_thumbnail=?, goods_image1=?, goods_image2=?, goods_description=? WHERE goods_id=?";
		    PreparedStatement pstmt = null;
	       try {
				this.conn = DbUtil.getConnection();
	           pstmt = conn.prepareStatement(sql);
	           pstmt.setString(1, goods.getGoodsName());
	           pstmt.setInt(2, goods.getGoodsCost());
	           pstmt.setString(3, goods.getThumbnail());
	           pstmt.setString(4, goods.getGoodsImage1());
	           pstmt.setString(5, goods.getGoodsImage2());
	           pstmt.setString(6, goods.getGoodsDescription());
	           pstmt.setLong(7, goods.getGoodsId());
	           pstmt.executeUpdate();
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	            DbUtil.close(conn);
			}
	   }

}
