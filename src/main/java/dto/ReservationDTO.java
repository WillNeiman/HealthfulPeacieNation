package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservationDTO {

	private Long reservationId;
	private String userId;
	private Timestamp exerciseDateTime;
	private int isState;
	private int isAwaiter;
	private Long ticketId;
	private Timestamp reservateAtDate;
	
	public ReservationDTO() {}
		
	public ReservationDTO(String userId, Timestamp exerciseDateTime) {
		this.userId = userId;
		this.exerciseDateTime = exerciseDateTime;
		}
	public ReservationDTO(Long reservationId, Timestamp exerciseDateTime){
		this.reservationId = reservationId;
		this.exerciseDateTime = exerciseDateTime;
	}
	
	//makeReservation에 사용
	public ReservationDTO(String userId, Timestamp exerciseDateTime, long ticketId){
		this.userId = userId;
		this.exerciseDateTime = exerciseDateTime;
		this.ticketId = ticketId;
	}
	
	public ReservationDTO(Timestamp exerciseDateTime, int isState, int isAwaiter){
		this.exerciseDateTime = exerciseDateTime;
		this.isState = isState;
		this.isAwaiter = isAwaiter;
	}
	
	public ReservationDTO(Timestamp exerciseDateTime, int isState, int isAwaiter, Timestamp reservateAtDate){
		this.exerciseDateTime = exerciseDateTime;
		this.isState = isState;
		this.isAwaiter = isAwaiter;
		this.reservateAtDate = reservateAtDate;
	}
	public ReservationDTO(Long reservationId, Timestamp exerciseDateTime, int isState, int isAwaiter, Timestamp reservateAtDate){
		this.reservationId = reservationId;
		this.exerciseDateTime = exerciseDateTime;
		this.isState = isState;
		this.isAwaiter = isAwaiter;
		this.reservateAtDate = reservateAtDate;
	}
	

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Timestamp getExerciseDateTime() {
		return exerciseDateTime;
	}

	public void setExerciseDateTime(Timestamp exerciseDateTime) {
		this.exerciseDateTime = exerciseDateTime;
	}

	public int getIsState() {
		return isState;
	}

	public void setIsState(int isState) {
		this.isState = isState;
	}

	public int getIsAwaiter() {
		return isAwaiter;
	}

	public void setIsAwaiter(int isAwaiter) {
		this.isAwaiter = isAwaiter;
	}

	public int isState() {
		return isState;
	}

	public void setState(int isState) {
		this.isState = isState;
	}

	public int isAwaiter() {
		return isAwaiter;
	}

	public void setAwaiter(int isAwaiter) {
		this.isAwaiter = isAwaiter;
	}

	public Timestamp getReservateAtDate() {
		return reservateAtDate;
	}

	public void setReservateAtDate(Timestamp reservateAtDate) {
		this.reservateAtDate = reservateAtDate;
	}

	

	
}
