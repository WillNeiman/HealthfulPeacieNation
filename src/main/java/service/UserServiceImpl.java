package service;

import java.util.List;

import dto.UserDTO;
import repository.SignupResult;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	public UserServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
	}

	@Override
	public SignupResult registerUser(String userId, String userPw, String userName, String userPhoneNumber, String userEmail) {
		UserDTO user = new UserDTO(userId, userPw, userName, userPhoneNumber, userEmail);
		SignupResult signupResult = 	userRepository.save(user);
		return signupResult;
		
	}

	@Override
	public List<UserDTO> getUserList() {
		List<UserDTO> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public UserDTO getUser(String userId) {
		UserDTO user = userRepository.findById(userId);
		return user;
	}

	@Override
	public void modifyUserInformation(String userId, String userPw, String userPhoneNumber,	String userEmail) {
		UserDTO user = new UserDTO(userId, userPw, userPhoneNumber, userEmail);
		userRepository.update(user);
	}

	@Override
	public void withdrawUser(String userId) {
		userRepository.delete(userId);
	}
	
	//濡�洹몄��
	@Override
	public LoginResult loginCheck(String userId, String userPw ) {
		List<UserDTO> users = getUserList();
		for (UserDTO userDTO : users) {
			if (userDTO.getUserId().equals(userId)) {
				if (userDTO.getUserPw().equals(userPw)) {
					return LoginResult.SUCCESS;
				}
			}
		}
		return LoginResult.FAILURE_INVALID_PW_OR_ID;
	}
	
	@Override
	public int getUserCount() {
		int count = userRepository.count();
		return count;
	}
	@Override
	public List<UserDTO> getUserListByRange(int start, int end){
		List<UserDTO> list = userRepository.getListByRange(start, end);
		return list;
	}

	@Override
	public int searchUserCount(String keyword) {
		int count = userRepository.getUserCountByKeyword(keyword);
		return count;
	}

	@Override
	public List<UserDTO> searchUserList(String keyword, int start, int end) {
		List<UserDTO> list = userRepository.getUsersByKeyword(keyword, start, end);
		return list;
	}

	@Override
	public void modifyUserPassword(String userId, String userPw) {
		UserDTO user = new UserDTO(userId, userPw);
		userRepository.updatePassword(user);
	}

}
