package dto;

import java.sql.*;

public class ReviewDTO {
	
// field
	private long reviewId;
	private long goodsId;
	private String userId;
	private long orderPk;
	private String reviewContent;
	private Timestamp reviewCreatedAt;
	
// Constructor
	public ReviewDTO(){
		
	}
	
	// save()에 사용(review 신규 등록)
	public ReviewDTO(long goodsId, String userId, Long orderPk, String reviewContent){
		this.goodsId = goodsId;
		this.userId = userId;
		this.orderPk = orderPk;
		this.reviewContent = reviewContent.replace(System.lineSeparator(), "<br>"); 
	}
	
	// update()에 사용(review 수정)
	public ReviewDTO(long reviewId, String reviewContent){
		this.reviewId = reviewId;
		this.reviewContent = reviewContent.replace(System.lineSeparator(), "<br>"); 
	}
	
	// findAll()에 사용(review 전체 조회)
	public ReviewDTO(long reviewId, long goodsId, String userId, Long orderPk, String reviewContent, Timestamp reviewCreatedAt){
		this.reviewId = reviewId;
		this.goodsId = goodsId;
		this.userId = userId;
		this.orderPk = orderPk;
		this.reviewContent = reviewContent.replace(System.lineSeparator(), "<br>"); 
		this.reviewCreatedAt = reviewCreatedAt;
	}
	
// getter & setter
	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Timestamp getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(Timestamp reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public long getOrderPk() {
		return orderPk;
	}

	public void setOrderPk(long orderPk) {
		this.orderPk = orderPk;
	}
	
}
