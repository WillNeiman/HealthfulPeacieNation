package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.TicketDTO;
import util.DbUtil;

public class TicketRepositoryImpl implements TicketRepository{

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public List<TicketDTO> findListById(String userId) {
		String sql = "select * from tickets where user_id=? order by ticket_created_at desc";
		List<TicketDTO> ticketList = new ArrayList<>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TicketDTO ticketDto = new TicketDTO();
				ticketDto.setTicketId(rs.getLong("ticket_id"));
				ticketDto.setTicketNumber(rs.getInt("ticket_number"));
				ticketDto.setTicketType(rs.getString("ticket_type"));
				ticketDto.setUserId(rs.getString("user_id"));
				ticketDto.setTicketCreatedAt(rs.getTimestamp("ticket_created_at"));	
				
				ticketList.add(ticketDto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		
		return ticketList;
	}

	@Override
	public TicketDTO findById(long ticketId) {
		
		String sql = "select * from tickets where ticket_id=?";
		TicketDTO ticketDto = new TicketDTO();		
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, ticketId);
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				ticketDto.setTicketNumber(rs.getInt("ticket_number"));
				ticketDto.setTicketType(rs.getString("ticket_type"));
				ticketDto.setUserId(rs.getString("user_id"));
				ticketDto.setTicketCreatedAt(rs.getTimestamp("ticket_created_at"));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            System.out.println("conn-closed");
            DbUtil.close(conn);
		}		
		return ticketDto;
	}

	//운동 티켓 반환(예약 취소)
	@Override
	public void update(long ticketId, int ticketNumber) {

		String sql = "update tickets set ticket_number=? where ticket_id=?";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketNumber);
			pstmt.setLong(2, ticketId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		
	}

	@Override
	public void save(TicketDTO ticketDto) {
		String sql = "insert into tickets (ticket_id, ticket_number, user_id, ticket_type) values (ticket_id_seq.nextval,?,?,?)";
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketDto.getTicketNumber());
			pstmt.setString(2, ticketDto.getUserId());
			pstmt.setString(3, ticketDto.getTicketType());
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}		
	}
	
	public List<TicketDTO> findAll(){
		String sql = "select * from tickets order by ticket_created_at desc";
		List<TicketDTO> ticketList = new ArrayList<>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				TicketDTO ticketDto = new TicketDTO();
				ticketDto.setTicketId(rs.getLong("ticket_id"));
				ticketDto.setTicketNumber(rs.getInt("ticket_number"));
				ticketDto.setTicketType(rs.getString("ticket_type"));
				ticketDto.setUserId(rs.getString("user_id"));
				ticketDto.setTicketCreatedAt(rs.getTimestamp("ticket_created_at"));					
				ticketList.add(ticketDto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DbUtil.close(conn);
		}
		
		return ticketList;
	}

}
