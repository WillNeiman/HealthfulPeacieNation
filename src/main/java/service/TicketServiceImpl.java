package service;

import java.util.List;

import dto.TicketDTO;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;

public class TicketServiceImpl implements TicketService {
	TicketRepository ticketRepository;

	public TicketServiceImpl(){
		this.ticketRepository = new TicketRepositoryImpl();
	}
	@Override
	public void create(TicketDTO ticketDto) {
		System.out.println("TicketServicelmpl - create");
		ticketRepository.save(ticketDto);
	}
	
	@Override
	public TicketDTO findById(long ticketId) {
		System.out.println("TicketServicelmpl - findById(Long ticketId)");
		TicketDTO ticketDto = ticketRepository.findById(ticketId);
		return ticketDto;
	}

	@Override
	public void update(long ticketId, int ticketNumber) {
		System.out.println("TicketServicelmpl - findById");
		ticketRepository.update(ticketId, ticketNumber);		
	}


	@Override
	public List<TicketDTO> findListById(String userId) {
		System.out.println("TicketServicelmpl - findListById");
		List<TicketDTO> ticketDto = ticketRepository.findListById(userId);
		return ticketDto;
		

	}
	@Override
	public List<TicketDTO> findAllTickets() {
		System.out.println("TicketServicelmpl - findListById");
		List<TicketDTO> ticketDto = ticketRepository.findAll();
		return ticketDto;
	}

}
