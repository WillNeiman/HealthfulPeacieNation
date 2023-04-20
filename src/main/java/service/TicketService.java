package service;

import java.util.List;

import dto.TicketDTO;

public interface TicketService {
	
	//개별 티켓 상세 조회
	TicketDTO findById(long ticketId);

	//운동 티켓 증감 업데이트 : ticket_number = ?
	void update(long ticketId,  int ticketNumber);
	
	//티켓 생성
	void create(TicketDTO ticketDto);
	
	//티켓 리스트 조회
	List<TicketDTO> findListById(String userId);
	
	List<TicketDTO> findAllTickets();
}
