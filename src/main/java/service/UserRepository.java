package service;

import java.util.List;

import dto.UserDTO;

public interface UserRepository {
	
	SignupResult save(UserDTO user);
	
	List<UserDTO> findAll();
	
	UserDTO findById(String userId);
	
	void update(UserDTO user);
	
	void delete(String userId);
	
	public int count();
	
	public List<UserDTO> getListByRange(int start, int end);

	public int getUserCountByKeyword(String keyword);
	
	public List<UserDTO> getUsersByKeyword(String keyword, int start, int end);
	
	public void updatePassword(UserDTO user);
}
