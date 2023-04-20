package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TicketDTO {
	private Long ticketId; // PK
	private int ticketNumber;
	private String userId;
	private String ticketType;
	private Timestamp ticketCreatedAt;
	
	public TicketDTO(){}
	
	public TicketDTO(int ticketNumber, String userId, String ticketType){
		this.ticketNumber = ticketNumber;
		this.userId = userId;
		this.ticketType = ticketType;		
	}
	
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		if(ticketNumber<0) {
			System.out.println("ticket number(운동 회원권 갯수)는 0보다 작을 수 없습니다.");
		}else {
			this.ticketNumber = ticketNumber;
		}
	}

	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
			this.ticketType = ticketType;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTicketCreatedAt() {
		return ticketCreatedAt;
	}

	public void setTicketCreatedAt(Timestamp ticketCreatedAt) {
		this.ticketCreatedAt = ticketCreatedAt;
	}
	

	
	

}
