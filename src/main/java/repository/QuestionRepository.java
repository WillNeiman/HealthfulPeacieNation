package repository;

import java.sql.Timestamp;
import java.util.List;

import dto.QuestionDTO;
import dto.UserDTO;

public interface QuestionRepository {
	//questionInput.jsp, questionPro.jsp
	//유저 게시글 리스트 조회 => myPost.jsp 혜지님
	//게시글 수정, 삭제 => postModify.jsp, adminPostModify.jsp 혜지님
	
	//게시글 작성 ←
	void save(QuestionDTO questionDTO);

	//글번호로 조회(상세보기) 정은님
	QuestionDTO findById(long questionId);
	//게시글 업데이트 (유저 : if( 답글 != null) 관리자 : 조건 X) ←

	void update(QuestionDTO questionDTO);
	//게시글 삭제 (유저 : if( 답글 != null) 관리자 : 조건 X) ←
	void delete(Long questionId);

	List<QuestionDTO> findAll();
	
	int getAllCount();
	
	public List<QuestionDTO> getListByRange(int startRow, int endRow);

	List<QuestionDTO> findById(String userId);
//	int getAllcountById(String userId);
	
	public int getAllCountById(String userId);
	
	public List<QuestionDTO> findByGoodsId(Long goodsId);
	
	public List<QuestionDTO> findTop5RecentQuestionsByGoodsId(Long goodsId);
	
	//답글 업데이트
	public void updateReply(long questionId, String reply, Timestamp replyCreatedAt);
	
	// 페이지네이션용
	 List<QuestionDTO> findByUserIdWithPagination(String userId, int startRow, int endRow);

}
