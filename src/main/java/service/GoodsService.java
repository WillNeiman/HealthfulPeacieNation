package service;

import java.util.List;

import dto.GoodsDTO;

public interface GoodsService {
	
	public void addGoods(String goodsName, int goodsCost, int goodsStock, String goodsThumbnail, String goodsImage1, String goodsImage2, String goodsDescription, String goodsCategory);

	public void updateGoodsStock(Long goodsId, int quantity);
	
	public GoodsDTO findGoods(Long goodsId);
	
	public List<GoodsDTO> findAllGoods();
	
	public void updateGoodsAverageScore(Long goodsId);
	
	public void modifyGoods(long goodsId, String goodsName, int goodsCost, String goodsThumbnail, String GoodsImage1, String GoodsImage2, String GoodsDescription);

}
