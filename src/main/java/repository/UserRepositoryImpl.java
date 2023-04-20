package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import dto.UserDTO;
import util.DbUtil;

public class UserRepositoryImpl implements UserRepository {

	Connection conn = null;

	@Override
	public SignupResult save(UserDTO user) {
		System.out.println("UserRepositoryImpl - save");
		String sql = "INSERT INTO users (user_pk, user_id, user_pw, user_name, user_phone_number, user_email) VALUES ((SELECT MAX(user_pk) FROM users) + 1, ?,?,?,?,?)";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserPhoneNumber());
			pstmt.setString(5, user.getUserEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return SignupResult.FAILURE_ID_ALREADY_EXIST;
		} finally {
            DbUtil.close(conn);
		}
		return SignupResult.SIGNUP_SUCCESS;
	}

	@Override
	public List<UserDTO> findAll() {
		System.out.println("UserRepositoryImpl - findAll");
		String sql = "SELECT * FROM USERS";
		ArrayList<UserDTO> userList = new ArrayList<>();
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long userPk = rs.getLong("user_pk");
				String userId = rs.getString("user_id");
				String userPw = rs.getString("user_pw");
				String userName = rs.getString("user_name");
				String userPhoneNumber = rs.getString("user_phone_number");
				String userEmail = rs.getString("user_email");
				Timestamp userCreatedAt = rs.getTimestamp("user_created_at");
				UserDTO users = new UserDTO(userId, userPw, userName, userPhoneNumber, userEmail, userCreatedAt, userPk);
				userList.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return userList;
	}

	@Override
	public UserDTO findById(String userId) {
		System.out.println("UserRepositoryImpl - findById");
		String sql = "SELECT * FROM USERS WHERE user_id = ?";
		UserDTO user = null;
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SQL 荑쇰━ ����");
			if (rs.next()) {
				String userPw = rs.getString("user_pw");
				System.out.println("userPw: " + userPw);
				String userName = rs.getString("user_name");
				System.out.println("userName: " + userName);
				String userPhoneNumber = rs.getString("user_phone_number");
				System.out.println("userPhoneNumber: " + userPhoneNumber);
				String userEmail = rs.getString("user_email");
				System.out.println("userEmail: " + userEmail);
				Timestamp userCreatedAt = rs.getTimestamp("user_created_at");
				System.out.println("userCreatedAt: " + userCreatedAt);
				long userPk = rs.getLong("user_pk");
				user = new UserDTO(userId, userPw, userName, userPhoneNumber, userEmail, userCreatedAt, userPk);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		System.out.println("user 媛�泥� 諛���");
		return user;
	}

	@Override
	public void update(UserDTO user) {
		System.out.println("UserRepositoryImpl - update");
		String sql = "UPDATE users SET user_pw = ?, user_phone_number = ?, user_email = ? WHERE user_id = ?";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserPhoneNumber());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
	}

	@Override
	public void delete(String userId) {
		System.out.println("UserRepositoryImpl - delete");
		String sql = "DELETE FROM users WHERE user_id = ?";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
	}

	// 珥� ���� �� 援ы��湲�
	@Override
	public int count() {
		System.out.println("UserRepositoryImpl - count");
		int count = 0;
		String sql = "SELECT COUNT(*) FROM users";

		try {
			this.conn = DbUtil.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return count;
	}

	// �뱀�� �レ�� �⑥��濡� �����댁�� List�� �댁�� ���ы��湲�
	@Override
	public List<UserDTO> getListByRange(int start, int end) {
		System.out.println("UserRepositoryImpl - getListByRange");
		List<UserDTO> users = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM users ORDER BY user_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";

		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long userPk = rs.getLong("user_pk");
				String userId = rs.getString("user_id");
				String userPw = rs.getString("user_pw");
				String userName = rs.getString("user_name");
				String userPhoneNumber = rs.getString("user_phone_number");
				String userEmail = rs.getString("user_email");
				Timestamp userCreatedAt = rs.getTimestamp("user_created_at");

				UserDTO user = new UserDTO(userId, userPw, userName, userPhoneNumber, userEmail, userCreatedAt, userPk);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		return users;
	}

	@Override 
	public List<UserDTO> getUsersByKeyword(String keyword, int start, int end) {
		System.out.println("UserRepositoryImpl - getUsersByKeyword");
	    List<UserDTO> users = new ArrayList<>();
	    String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, p.* FROM (SELECT * FROM users WHERE user_id LIKE ? OR user_name LIKE ? OR user_phone_number LIKE ? OR user_email LIKE ? ORDER BY user_created_at DESC) p) WHERE rnum >= ? AND rnum <= ?";

	    try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + keyword + "%");
	        pstmt.setString(2, "%" + keyword + "%");
	        pstmt.setString(3, "%" + keyword + "%");
	        pstmt.setString(4, "%" + keyword + "%");
	        pstmt.setInt(5, start);
	        pstmt.setInt(6, end);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	long userPk = rs.getLong("user_pk");
	            String userId = rs.getString("user_id");
	            String userPw = rs.getString("user_pw");
	            String userName = rs.getString("user_name");
	            String userPhoneNumber = rs.getString("user_phone_number");
	            String userEmail = rs.getString("user_email");
	            Timestamp userCreatedAt = rs.getTimestamp("user_created_at");

	            UserDTO user = new UserDTO(userId, userPw, userName, userPhoneNumber, userEmail, userCreatedAt, userPk);
	            users.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            DbUtil.close(conn);
		}
	    return users;
	}

	@Override
	public int getUserCountByKeyword(String keyword) {
		System.out.println("UserRepositoryImpl - getUserCountByKeyword");
	    int count = 0;
	    String sql = "SELECT COUNT(*) AS count FROM users WHERE user_id LIKE ? OR user_name LIKE ? OR user_phone_number LIKE ? OR user_email LIKE ?";

	    try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + keyword + "%");
	        pstmt.setString(2, "%" + keyword + "%");
	        pstmt.setString(3, "%" + keyword + "%");
	        pstmt.setString(4, "%" + keyword + "%");
	        ResultSet rs = pstmt.executeQuery();
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
	public void updatePassword(UserDTO user) {
		System.out.println("UserRepositoryImpl - updatePassword");
		String sql = "UPDATE users SET user_pw = ? WHERE user_id = ?";
		try {
			this.conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		
	}
	
}
