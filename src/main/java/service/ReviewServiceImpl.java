package service;

import java.util.*;

import dto.*;
import repository.*;

public class ReviewServiceImpl implements ReviewService {

	ReviewRepository reviewRepository = new ReviewRepositoryImpl();
	OrderGoodsService orderGoodsService = new OrderGoodsServiceImpl();
	
	@Override
	public void writeReview( long goodsId, String userId, long orderPk, String reviewContent, int rating) {
	    System.out.println("ReviewServiceImpl - writeReview");
		ReviewDTO review = new ReviewDTO(goodsId, userId, orderPk, reviewContent);
		reviewRepository.save(review);
		orderGoodsService.updateReviewWritten(orderPk);
		orderGoodsService.updateRating(orderPk, rating);
	}
	
	@Override
	public void modifyReview(long reviewId, String reviewContent) {
	    System.out.println("ReviewServiceImpl - modifyReview");
		ReviewDTO review = new ReviewDTO(reviewId, reviewContent);
		reviewRepository.update(review);
	}
	
	@Override
	public void deleteReview(long reviewId) {
	    System.out.println("ReviewServiceImpl - deleteReview");
		reviewRepository.delete(reviewId);
	}
	
	@Override
	public ReviewDTO getReview(long reviewId) {
	    System.out.println("ReviewServiceImpl - getReview");
		ReviewDTO review = reviewRepository.select(reviewId);
		return review;
	}
	
	@Override
	public List<ReviewDTO> getReviewList(long goodsId) {
	    System.out.println("ReviewServiceImpl - getReviewList");
		List<ReviewDTO> reviewList = reviewRepository.findAllById(goodsId);
		return reviewList;
	}
	
	@Override
	public List<ReviewDTO> getReviewList(String userId) {
	    System.out.println("ReviewServiceImpl - getReviewList");
		List<ReviewDTO> reviewList = reviewRepository.findAllById(userId);
		return reviewList;
	}
	
	@Override
	public List<ReviewDTO> getReviewList() {
	    System.out.println("ReviewServiceImpl - getReviewList");
		List<ReviewDTO> reviewList = reviewRepository.findAll();
		return reviewList;
	}
	
	@Override
    public List<ReviewDTO> getRecentThreeReviews(long goodsId) {
	    System.out.println("ReviewServiceImpl - getRecentThreeReviews");
        return reviewRepository.findRecentThreeById(goodsId);
    }
}
