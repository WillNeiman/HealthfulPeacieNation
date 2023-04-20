package service;

import java.util.List;

import dto.GoodsDTO;
import dto.OrderGoodsDTO;
import repository.GoodsRepository;
import repository.GoodsRepositoryImpl;
import repository.OrderGoodsRepository;
import repository.OrderGoodsRepositoryImpl;

public class GoodsServiceImpl implements GoodsService{
	
	GoodsRepository goodsRepository = new GoodsRepositoryImpl();
	OrderGoodsRepository orderGoodsRepository = new OrderGoodsRepositoryImpl();
	
	@Override
	public void addGoods(String goodsName, int goodsCost, int goodsStock, String thumbnail, String goodsImage1, String goodsImage2, String goodsDescription, String goodsCategory) {
		System.out.println("GoodsServiceImpl - addGoods");
		GoodsDTO goods = new GoodsDTO();
		goods.setGoodsName(goodsName);
		goods.setGoodsCost(goodsCost);
		goods.setGoodsStock(goodsStock);
		goods.setThumbnail(thumbnail);
		goods.setGoodsImage1(goodsImage1);
		goods.setGoodsImage2(goodsImage2);
		goods.setGoodsDescription(goodsDescription);
		goods.setGoodsCategory(goodsCategory);

		goodsRepository.save(goods);
		System.out.println("addGoods ���� ���� ��猷�");
	}

	@Override
	public void updateGoodsStock(Long goodsId, int quantity) {
		System.out.println("GoodsServiceImpl - updateGoodsStock");
		GoodsRepository goodsRepository = new GoodsRepositoryImpl();
		GoodsDTO goods = goodsRepository.findById(goodsId);
		goodsRepository.updateGoodsStock(goods, quantity);
		System.out.println("updateGoodsStock �ш� ���� ��猷�");
	}

	@Override
	public GoodsDTO findGoods(Long goodsId) {
	    System.out.println("GoodsServiceImpl - findGoods");
		return goodsRepository.findById(goodsId);
	}
	
	@Override
	public List<GoodsDTO> findAllGoods() {
	    System.out.println("GoodsServiceImpl - findAll");
	    List<GoodsDTO> goodsList = goodsRepository.findAll();
	    return goodsList;
	}

	@Override
	public void updateGoodsAverageScore(Long goodsId) {
	    System.out.println("GoodsServiceImpl - updateGoodsAverageScore");
	    
		// �대�� ������ ���� 由щ럭 ���� 諛� ������ ���� 二쇰Ц ���� �곗�댄�곕�� 媛��몄��
	    List<OrderGoodsDTO> orderGoodsList = orderGoodsRepository.findByReviewedAndRated(goodsId);
	    if (orderGoodsList.isEmpty()) {
	        System.out.println("由щ럭媛� ���깅���댁���쇰㈃�� ���댄���� 0�� ���� 媛��� 遺��ъ�ㅼ� 紐삵��");
	        return;
	    }
	    
	    // 由щ럭 ���� 諛� ������ ���� 二쇰Ц ���� �곗�댄�곕�� �댁�⑺�� 蹂��� �⑷��� 由щ럭 媛��� 怨���
	    int totalRating = 0;
	    for (OrderGoodsDTO orderGoods : orderGoodsList) {
	        totalRating += orderGoods.getRating();
	        System.out.println("���댄�� �몄� �깃났: " + orderGoods.getRating());
	    }
	    // ��洹� 蹂��� 怨���
	    double averageRating = (double) totalRating / orderGoodsList.size();

	 // 怨��곕�� ��洹� 蹂����� �대�� ������ goods_score 而щ�쇱�� ����
	    goodsRepository.updateGoodsScore(goodsId, averageRating);
		
	}
	
	@Override
	   public void modifyGoods(long goodsId, String goodsName, int goodsCost, String goodsThumbnail, String GoodsImage1, String GoodsImage2, String GoodsDescription) {
	      System.out.println("GoodsServiceImpl - modifyGoods");
	      GoodsDTO goods = new GoodsDTO(goodsId, goodsName, goodsCost, goodsThumbnail, GoodsImage1, GoodsImage2, GoodsDescription);
	      goodsRepository.update(goods);
	   }


}
