package dto;

import java.sql.Timestamp;

public class QuestionDTO {
	private long questionId;//PK
	private String userId;
	private String category;
	private Long goodsId;
	private String title;
	private String content;
	private Timestamp createdAt;
	private String reply;
	private Timestamp updatedAt;
	
	public QuestionDTO() {
	}
	
	//글 작성에 쓰인다
	public QuestionDTO(String userId, String category, Long goodsId, String title, String content, String reply){
		this.userId = userId;
		this.category = category;
		this.goodsId = goodsId;
		this.title = title;
		this.content = content;
		this.reply = reply;
	}
	
	//글 수정에 쓰인다
	public QuestionDTO(long questionId, String userId, String category, Long goodsId, String title, String content, String reply){
		this.questionId = questionId;
		this.userId = userId;
		this.category = category;
		this.goodsId = goodsId;
		this.title = title;
		this.content = content;
		this.reply = reply;
	}
	
	//조회에 쓰인다
	public QuestionDTO(long questionId, String userId, String category, Long goodsId, String title, String content, Timestamp createdAt, String reply, Timestamp updatedAt){
		this.questionId = questionId;
		this.userId = userId;
		this.category = category;
		this.goodsId = goodsId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.reply = reply;
		this.updatedAt = updatedAt;
	}
	
	//문의글 작성 시 사용
	public QuestionDTO(String userId, String category, Long goodsId, String title, String content) {
		this.userId = userId;
		this.category = category;
		this.goodsId = goodsId;
		this.title = title;
		this.content = content;
	}

	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content.replace(System.lineSeparator(), "<br>");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	
}
