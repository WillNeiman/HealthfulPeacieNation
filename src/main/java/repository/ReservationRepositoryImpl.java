package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.ReservationDTO;
import util.DbUtil;

public class ReservationRepositoryImpl implements ReservationRepository {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int countOverlap(ReservationDTO reservation) {
		System.out.println("ReservationRepositoryImpl - countOverlap");
		int count = 0;
		String sql = "select count(*) from reservations where user_id=? and exercise_date_time=? and isState=? and isAwaiter=?";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getUserId());
			pstmt.setTimestamp(2, reservation.getExerciseDateTime());
			pstmt.setInt(3, reservation.getIsState());
			pstmt.setInt(4, reservation.getIsAwaiter());
			rs = pstmt.executeQuery();
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

	@Override
	public List<ReservationDTO> findListById(String userId) {
		System.out.println("ReservationRepositoryImpl - findListById - parameter: " + userId);
		String sql = "select * from reservations where user_id=? order by exercise_date_time desc";
		List<ReservationDTO> reservationDtoList = new ArrayList<>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationDTO reservationDto = new ReservationDTO();
				reservationDto.setUserId(rs.getString("user_id"));
				reservationDto.setReservationId(rs.getLong(1));
				reservationDto.setExerciseDateTime(rs.getTimestamp("exercise_date_time"));
				reservationDto.setState(rs.getInt("isState"));
				reservationDto.setAwaiter(rs.getInt("isAwaiter"));
				reservationDto.setTicketId(rs.getLong("ticket_id"));
				reservationDto.setReservateAtDate(rs.getTimestamp("reservation_created_at"));
				reservationDtoList.add(reservationDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return reservationDtoList;
	}
	 @Override
	   public int countParticipants(ReservationDTO reservation) {
	      String sql = "select count(*) from reservations where exercise_date_time=? and isState='1' and isAwaiter='0'";
	      int count = 0;
	      try {
			 conn = DbUtil.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setTimestamp(1, reservation.getExerciseDateTime());
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            count = rs.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
				DbUtil.close(conn);
			}
	      
	      
	      return count;
	   }

	@Override
	public int save(ReservationDTO reservation) {
		System.out.println("ReservationRepositoryImpl - save");
		String sql = "insert into reservations "
				+ "(reservation_id, user_id, exercise_date_time, isState, isAwaiter, reservation_created_at, ticket_id) "
				+ "values (reservations_seq.nextval,?,?,?,?,SYSTIMESTAMP,?)";
		int result = 1;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getUserId());
			System.out.println(reservation.getUserId());
			pstmt.setTimestamp(2, reservation.getExerciseDateTime());
			System.out.println(reservation.getExerciseDateTime());
			pstmt.setInt(3, reservation.isState());
			System.out.println(reservation.isState());
			pstmt.setInt(4, reservation.isAwaiter());
			System.out.println(reservation.isAwaiter());
			pstmt.setLong(5, reservation.getTicketId());
			System.out.println(reservation.getTicketId());
			pstmt.executeUpdate();
			result = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return result;
	}

	// ���� 踰��몃� 媛��몄�ㅺ린
	@Override
	public ReservationDTO findById(long reservationId) {
		System.out.println("ReservationRepositoryImpl - findById");
		String sql = "select * from reservations where reservation_id=?";
		ReservationDTO reservationDto = new ReservationDTO();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, reservationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				reservationDto.setUserId(rs.getString("user_id"));
				reservationDto.setReservationId(rs.getLong("reservation_id"));
				reservationDto.setExerciseDateTime(rs.getTimestamp("exercise_date_time"));
				reservationDto.setState(rs.getInt("isState"));
				reservationDto.setAwaiter(rs.getInt("isAwaiter"));
				reservationDto.setReservateAtDate(rs.getTimestamp("reservation_created_at"));
				reservationDto.setTicketId(rs.getLong("ticket_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return reservationDto;
	}

	@Override
	public void update(long reservationId, int isState, int isWaiter) {
		System.out.println("ReservationRepositoryImpl - update");
		String sql = "update reservations set isState=?, isAwaiter=? where reservation_id=?";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isState);
			pstmt.setInt(2, isWaiter);
			pstmt.setLong(3, reservationId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
	}

	@Override
	public void delete(long reservationId) {
		System.out.println("ReservationRepositoryImpl - delete");
		String sql = "delete reservations where reservation_id=?";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, reservationId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
	}

	@Override
	public List<Long> findReservationIdListById(String userId) {
		System.out.println("ReservationRepositoryImpl - findReservationIdListById");
		String sql = "select reservation_id from reservations where isAwaiter = '1' and user_id = ?";
		List<Long> List = new ArrayList<>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long reservationId = rs.getLong(1);
				List.add(reservationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return List;
	}

	public List<ReservationDTO> findReservationInfoById(Long reservationId) {
		System.out.println("ReservationRepositoryImpl - findReservationInfoById");
		String sql = "select * from reservations where reservation_id = ?";
		ArrayList<ReservationDTO> List = new ArrayList<>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, reservationId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Timestamp exerciseDateTime = rs.getTimestamp("exercise_date_time");
				ReservationDTO RDTO = new ReservationDTO(reservationId, exerciseDateTime);
				List.add(RDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
		return List;
	}

	
	@Override
	public List<ReservationDTO> findAll() {
	    System.out.println("ReservationRepositoryImpl - findAll");
	    String sql = "SELECT r.USER_ID, r.EXERCISE_DATE_TIME, r.ISSTATE, r.ISAWAITER, r.TICKET_ID, r.RESERVATION_CREATED_AT, r.RESERVATION_ID "
	            + "FROM reservations r "
	            + "JOIN tickets t ON r.TICKET_ID = t.TICKET_ID "
	            + "ORDER BY r.EXERCISE_DATE_TIME DESC, t.TICKET_TYPE DESC, t.TICKET_CREATED_AT DESC";

	    List<ReservationDTO> reservationDtoList = new ArrayList<>();
	    try {
	        conn = DbUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            ReservationDTO reservationDto = new ReservationDTO();
	            reservationDto.setUserId(rs.getString("USER_ID"));
	            reservationDto.setReservationId(rs.getLong("RESERVATION_ID"));
	            reservationDto.setExerciseDateTime(rs.getTimestamp("EXERCISE_DATE_TIME"));
	            reservationDto.setState(rs.getInt("ISSTATE"));
	            reservationDto.setAwaiter(rs.getInt("ISAWAITER"));
	            reservationDto.setTicketId(rs.getLong("TICKET_ID"));
	            reservationDto.setReservateAtDate(rs.getTimestamp("RESERVATION_CREATED_AT"));
	            reservationDtoList.add(reservationDto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DbUtil.close(conn);
	    }
	    return reservationDtoList;
	}

}
