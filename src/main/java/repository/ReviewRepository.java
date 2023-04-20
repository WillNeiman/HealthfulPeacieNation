package repository;

import java.util.*;
import dto.*;

public interface ReviewRepository {
	
	// 새 리뷰 작성
	void save(ReviewDTO review);
	
	// 리뷰 수정
	void update(ReviewDTO review);
	
	// 리뷰 삭제
	void delete(long reviewId);
	
	// 특정 리뷰 불러오기
	ReviewDTO select(long reviewId); // reviewId로 리뷰 정보 조회
	
	// 상품 상세보기에서 해당 상품 리뷰 전체보기
	public List<ReviewDTO> findAllById(long goodsId);
	
	// 마이페이지에서 본인이 작성한 리뷰 전체보기
	public List<ReviewDTO> findAllById(String userId);
	
	// 모든 리뷰 출력(관리자 모드 내에서 구현)
	public List<ReviewDTO> findAll();
	
	public List<ReviewDTO> findRecentThreeById(long goodsId);
}
