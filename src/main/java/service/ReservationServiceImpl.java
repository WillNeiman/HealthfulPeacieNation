package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import dto.ReservationDTO;
import dto.TicketDTO;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import util.DbUtil;

public class ReservationServiceImpl implements ReservationService{

	static final int TOTAL_PARTICIPANT = 2;
	static final int PT = 1; //pt留� 李몄�ъ�몄�� 1
	static Connection conn;
	ReservationRepository reservationRepository;
	
	public ReservationServiceImpl(){
		this.reservationRepository = new ReservationRepositoryImpl();
		
	}

	@Override
	public int makeReservation(ReservationDTO reservationDto) {
		System.out.println("ReservationServicelmpl - makeReservation");
		
		int status = 0;
			//由ъ�ㅽ�몄�� 以�蹂듬���� ���쎌�� ����吏� ����, count != 0 �대㈃ 以�蹂� ���쎌�� ����
		reservationDto.setAwaiter(0);
		reservationDto.setIsState(1);
			int count = reservationRepository.countOverlap(reservationDto);
		if(count == 0) {
				//��湲곗��濡� ���쎈������吏� ����, count != 0 �대㈃ 以�蹂� ���쎌�� ����

					int countPer = reservationRepository.countParticipants(reservationDto);
					System.out.println("���� 李몄�ъ�몄��: "+countPer);
					//李몄�� �몄�� < 珥� �몄�� = ���� �깃났
					if(countPer < TOTAL_PARTICIPANT) {
						reservationDto.setState(1);
						reservationDto.setAwaiter(0);			
						int result = 	reservationRepository.save(reservationDto);
						if(result == 0) {
						System.out.println("���쎌�� �깃났���듬����.");
						status = 1;
						}
					}else {
						//!(李몄�� �몄�� < 珥� �몄��) = ��湲곗��濡� ����
						System.out.println("������ 珥�怨쇳���듬����. ��湲곗��濡� ���쎈�⑸����.");
						reservationDto.setState(0);
						reservationDto.setAwaiter(1);
						reservationRepository.save(reservationDto);
						System.out.println("��湲곗��濡� ���쎈�����듬����.");
						status = 2;		
					}

			}else {
				
				System.out.println("以�蹂� ���쎌������.");
				status = 3;			
			}
			return status;
	}


	@Override
	public List<ReservationDTO> getListById(String userId) {
		System.out.println("ReservationServicelmpl - getListById");
		List<ReservationDTO> reservationList = reservationRepository.findListById(userId);

		return reservationList;
	}

	//isState = ���쎌����, 1=���깊��, 0=鍮����깊�� / isWaiter = ��湲�, 1=true, 0=false
	//���� 痍⑥����湲�
	@Override
	public void cancelReservation(long reservationId) {
		System.out.println("ReservationServicelmpl - cancelReservation");
		int isState = 0; int isWaiter = 0;
		reservationRepository.update(reservationId, isState, isWaiter);
	}
	//���쎌��瑜� ���� �����쇰� ���곗�댄��
	@Override
	public int waiterUpgrade(long reservationId) {
		System.out.println("ReservationServicelmpl - waiterUpgrade");
		ReservationDTO reservationDto = reservationRepository.findById(reservationId);
		int countPer = reservationRepository.countParticipants(reservationDto);
			//李몄�� �몄�� < 珥� �몄�� = ���� �깃났
		if(countPer < TOTAL_PARTICIPANT) {
			int isState = 1; int isWaiter = 0;
			reservationRepository.update(reservationId, isState, isWaiter);
			System.out.println("�ㅽ��1");
			return 0;
		}else {
			System.out.println("�ㅽ��2");
			return countPer;
		}		
	}

//	//痍⑥���� ���쎌�� ���쎌�쇰� ����由ш린
//	@Override
//	public void returnCancel(long reservationId) {
//		System.out.println("ReservationServicelmpl - returnCancel");
//		int isState = 1; int isWaiter = 0;
//		reservationRepository.update(reservationId, isState, isWaiter);
//	}

	@Override
	public void deleteWait(long reservationId) {
		System.out.println("ReservationServicelmpl - deleteWait");
		reservationRepository.delete(reservationId);
	}

	@Override
	public ReservationDTO getById(long reservationId) {
		System.out.println("ReservationServicelmpl - getById");
		ReservationDTO reservationDto = reservationRepository.findById(reservationId);		
		return reservationDto;
	}

	
	int isState = 1; //痍⑥����硫� 0�쇰�
	int isAwaiter = 0; //��湲곗���� 1
	static int limitation = 3; //���� �몄�� ����
	int ticketId = 0;
	//String ticketType = "0"; // 0�� 1:X PT(1:1)�� 1.
	
	

	
	@Override
	public ReservationDTO getReservation(long reservationId) {
		
		ReservationDTO RDTO = reservationRepository.findById(reservationId);
		//userId濡� 由ъ�ㅽ�몃�� 遺��ъ�� �ㅼ�� ���� 議곌굔�� 湲곗��쇰� ��諛�肄���濡� 紐⑸��� 遺�瑜��⑸����.
		//���쏀�� �대�� ��吏�(由ъ�ㅽ��) : ���� ��吏� �댄�� 異���
		//���� 痍⑥�� �댁��(由ъ�ㅽ��) : isState(False) 紐⑸�
		//吏��� ����(由ъ�ㅽ��) : ���� ��吏� �댁�� && isState(True)
		
		return RDTO;
	}

	
	
	//��湲� ���� 以� ��由ш� �� ������ ����吏� ����
	public List<Long> alertMe(String userId) {
		ReservationRepository reservationRepository = new ReservationRepositoryImpl();
		List<Long> alert = new ArrayList<>();
		List<ReservationDTO> myReservationList = reservationRepository.findListById(userId);
		if (myReservationList != null) {
			//dto 由ъ�ㅽ�� ��遺� �� 遺��ъ����, dto濡� 履쇨� �� awaiter=1�� reservationId 諛�����.
			for (int i = 0; i < myReservationList.size(); i++) {
				ReservationDTO reservationDTO = myReservationList.get(i);
				int awaiter = reservationDTO.getIsAwaiter();
				if (awaiter == 1) {
					long reservationId = reservationDTO.getReservationId();
					//�대������, 媛���, ��媛��� ���� ��
					List<ReservationDTO> List = reservationRepository.findReservationInfoById(reservationId);
			        if (List != null) {
						for (int j = 0; j < List.size(); j++) {
							ReservationDTO check = List.get(j);
							int countPer = reservationRepository.countParticipants(reservationDTO);	
							//System.out.println("countPer : " + countPer);
							if (countPer < TOTAL_PARTICIPANT) {
					        	long alertReservationId = check.getReservationId();
					        	alert.add(alertReservationId);
					        	System.out.println("李몄�� 媛��ν�� ���� ���대�� : " + alertReservationId);

					        }
						}
			        }
				}
			}
		}
		return alert;
	}

	@Override
	public List<ReservationDTO> getLists() {
		List<ReservationDTO> reservationList =  reservationRepository.findAll();
		return reservationList;
	}

	 public boolean changeList(long reservationId) {
	      boolean change = false;
	      ReservationRepository reservationRepository = new ReservationRepositoryImpl();
	      ReservationDTO myReservation = reservationRepository.findById(reservationId);
	      int count = reservationRepository.countParticipants(myReservation);
	      
	      if (count < TOTAL_PARTICIPANT) {
	         change = true;
	         
	      }
	      return change;
	   }

	 
	 
}
