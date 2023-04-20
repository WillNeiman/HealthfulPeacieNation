package repository;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import dto.*;

public interface ReservationRepository {
	
	List<ReservationDTO> findListById(String userId);
	List<ReservationDTO> findAll();
	
	//lesson 현재 참여 인원 반환
	int countParticipants(ReservationDTO reservation);

	//유저아이디, 운동 종류, 트레이너, 운동 시간 값이 모두 일치하하고 isState(예약 활성화 1=예약,0=예약취소), isWaiter(1=대기자,0=false) 값은 설정해서 예약 확인
	public int countOverlap(ReservationDTO reservation);

	int save(ReservationDTO reservation);
	
	ReservationDTO findById(long reservationId);
	
	void update(long reservationId, int isState, int isWaiter);
	
	void delete(long reservationId);
	
		//대기 예약 중 자리가 난 수업이 있는지 확인
		List<Long> findReservationIdListById(String userId);
		//대기중인 예약들을 조회하여 빈 자리가 났는지 확인
		List<ReservationDTO> findReservationInfoById(Long reservationId);
		
}
