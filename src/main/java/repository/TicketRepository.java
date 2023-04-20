package repository;

import java.util.List;

import dto.*;

public interface TicketRepository {

	List<TicketDTO> findListById(String userId);
	
	TicketDTO findById(long ticketId);
	
	void update(long ticketId, int ticketNumber);
	
	void save(TicketDTO ticketDto);
	
	List<TicketDTO> findAll();
}
