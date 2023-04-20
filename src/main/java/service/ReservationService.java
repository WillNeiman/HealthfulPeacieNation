package service;

import java.util.List;

import dto.*;

public interface ReservationService {
	
	//예약하기
	int makeReservation(ReservationDTO reservationDto) ;
	
	//예약 리스트
	List<ReservationDTO> getListById(String userId);
	List<ReservationDTO> getLists();
	
	//예약 취소하기
	void cancelReservation(long reservationId);
	
	//예약자를 예약 확정으로 업데이트
	int waiterUpgrade(long reservationId);
	
	//취소된 예약을 예약으로 되돌리기
//	void returnCancel(long reservationId);
	
	//대기 중인 예약 삭제(유저)
	void deleteWait(long reservationId);
	
	//예약된 DTO 하나 반환
	ReservationDTO getById(long reservationId);

	
		//예약 상세보기
		ReservationDTO getReservation(long reservationId);
		
		//대기 예약 중 자리가 난 수업이 있는지 확인
		List<Long> alertMe(String userId);
		 public boolean changeList(long reservationId);
}

