package service;

import java.util.*;
import dto.*;

public interface ReviewService {
	
	// 새 리뷰 작성
	public void writeReview( long goodsId, String userId, long orderPk, String reviewContent, int rating);
	
	// 리뷰 수정
	public void modifyReview(long reviewId, String reviewContent);
	
	// 리뷰 삭제
	public void deleteReview(long reviewId);
	
	// 리뷰 상세보기
	public ReviewDTO getReview(long reviewId);
	
	// 상품 상세보기에서 해당 상품 리뷰 전체보기
	public List<ReviewDTO> getReviewList(long goodsId);
	
	// 마이페이지에서 본인이 작성한 리뷰 전체보기
	public List<ReviewDTO> getReviewList(String userId);
	
	// 모든 리뷰 출력(관리자 모드 내에서 구현)
	public List<ReviewDTO> getReviewList();
	
	public List<ReviewDTO> getRecentThreeReviews(long goodsId);
}
