package service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.QuestionDTO;
import dto.UserDTO;
import repository.QuestionRepository;
import repository.QuestionRepositoryImpl;

public class QuestionServiceImpl implements QuestionService {

	QuestionRepository questionRepository;

	public QuestionServiceImpl() {
		this.questionRepository = new QuestionRepositoryImpl();
	}

	@Override
	public void createQuestion(String userId, String category, Long goodsId, String title, String content) {
		System.out.println("QuestionServiceImpl - createQuestion");
		QuestionDTO questionDTO = new QuestionDTO(userId, category, goodsId, title, content);
	
		questionRepository.save(questionDTO);
	}

	@Override
	public QuestionDTO getQuestion(Long questionId) {
		System.out.println("QuestionServiceImpl - getQuestion");
		QuestionDTO question = questionRepository.findById(questionId);
		return question;
	}

	@Override
	public void modifyQuestion(QuestionDTO questionDTO) {
		System.out.println("QuestionServiceImpl - modifyQuestion");
		String reply = questionDTO.getReply();
		String userId = questionDTO.getUserId();
		if (userId.equals("admin")) {
			if (reply.equals("null") || reply.equals("")) {
				System.out.println("!�듦� 怨듬��");
			}else {
				Timestamp currentTtimestamp = new Timestamp(System.currentTimeMillis());
				questionDTO.setUpdatedAt(currentTtimestamp);
				questionRepository.update(questionDTO);
			}
		}else
			questionRepository.update(questionDTO);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		System.out.println("QuestionServiceImpl - deleteQuestion");
		questionRepository.delete(questionId);
	}
	
	 @Override
	   public List<QuestionDTO> getList() {
			System.out.println("QuestionServiceImpl - getList");
	      List<QuestionDTO> questionDto = questionRepository.findAll();
	      return questionDto;
	   }
	@Override
	public int getAllCount() {
		System.out.println("QuestionServiceImpl - getAllCount");
		int count = questionRepository.getAllCount();
		return count;
	}

	@Override
	public List<QuestionDTO> getQuestionsWithPagenation(int startRow, int endRow) {
	    System.out.println("QuestionServiceImpl - getQuestionsWithPagenation");
	    System.out.println("startRow: " + startRow);
	    System.out.println("endRow: " + endRow);
	    return questionRepository.getListByRange(startRow, endRow);
	}

	@Override
	public List<QuestionDTO> getListById(String userId) {
		System.out.println("QuestionServiceImpl - getListById");
		List<QuestionDTO> questionDto = questionRepository.findById(userId);
	      return questionDto;
	}
	
	@Override
	public int getAllCountById(String userId) {
		System.out.println("QuestionServiceImpl - getAllCountById");
	    int count = questionRepository.getAllCountById(userId);
	    return count;
	}

	@Override
	public List<QuestionDTO> getQuestionsByGoodsId(Long goodsId) {
		System.out.println("QuestionServiceImpl - getQuestionsByGoodsId");
	    return questionRepository.findByGoodsId(goodsId);
	}
	
	@Override
    public List<QuestionDTO> getTop5RecentQuestionsByGoodsId(Long goodsId) {
        System.out.println("QuestionServiceImpl - getTop5RecentQuestionsByGoodsId");
        List<QuestionDTO> questionDto = questionRepository.findTop5RecentQuestionsByGoodsId(goodsId);
        return questionDto;
    }
	
	@Override
    public void updateReply(long questionId, String reply) {
        System.out.println("QuestionServiceImpl - updateReply");
        QuestionRepository questionRepository = new QuestionRepositoryImpl();
        Timestamp replyCreatedAt = new Timestamp(System.currentTimeMillis());
        questionRepository.updateReply(questionId, reply, replyCreatedAt);
    }
	
	@Override
	public List<QuestionDTO> getQuestionsByUserId(String userId, int startRow, int endRow) {
	    System.out.println("QuestionServiceImpl - getQuestionsByUserId");
	    System.out.println("userId: " + userId);
	    System.out.println("startRow: " + startRow);
	    System.out.println("endRow: " + endRow);
	    return questionRepository.findByUserIdWithPagination(userId, startRow, endRow);
	}
}
