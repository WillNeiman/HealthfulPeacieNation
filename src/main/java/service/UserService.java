package service;

import java.util.List;

import dto.UserDTO;
import repository.SignupResult;

public interface UserService {
	
	//회원가입
	SignupResult registerUser(String userId, String userPw, String userName, String userPhoneNumber, String userEmail);
	
	//회원 리스트
	List<UserDTO> getUserList();
	
	//아이디로 회원정보 찾기
	UserDTO getUser(String userId);
	
	//회원 정보 수정
	void modifyUserInformation(String userId, String userPw, String userPhoneNumber, String userEmail);
	
	//회원 탈퇴
	void withdrawUser(String userId);

	//로그인 체크
	LoginResult loginCheck(String userId, String userPw);
	
	public int getUserCount();
	
	public List<UserDTO> getUserListByRange(int start, int end);
	
	//검색어가 포함되는 회원 수 찾기
	public int searchUserCount(String keyword);
	
	//검색어가 포함되는 회원 리스트
	public List<UserDTO> searchUserList(String keyword, int start, int end);
	
	//관리자모드에서 특정 회원 비밀번호 변경
	public void modifyUserPassword(String userId, String userPw);
}
