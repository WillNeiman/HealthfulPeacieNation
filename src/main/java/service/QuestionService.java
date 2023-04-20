package service;
import java.util.List;
import dto.QuestionDTO;
import dto.UserDTO;




public interface QuestionService {
	
	//문의 작성
	void createQuestion(String userId, String category, Long goodsId, String title, String content);
	
	//문의글 디테일
	QuestionDTO getQuestion(Long questionId);
	
	//게시글 수정(유저) =>
	void modifyQuestion(QuestionDTO questionDTO);
	
	//게시글 삭제 => 유저: 답변완료 여부 확인
	void deleteQuestion(Long questionId);

	List<QuestionDTO> getList();
	
	int getAllCount();
	
	public List<QuestionDTO> getQuestionsWithPagenation(int startRow, int endRow);
	
	List<QuestionDTO> getListById(String userId);
	
	int getAllCountById(String userId);
	
	public List<QuestionDTO> getQuestionsByGoodsId(Long goodsId);
	
	public List<QuestionDTO> getTop5RecentQuestionsByGoodsId(Long goodsId);
	
	void updateReply(long questionId, String reply);
	
	List<QuestionDTO> getQuestionsByUserId(String userId, int startRow, int endRow);
	
}
