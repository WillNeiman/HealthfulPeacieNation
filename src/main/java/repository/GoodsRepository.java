package repository;

import java.util.List;

import dto.GoodsDTO;

public interface GoodsRepository {
	
	public void save(GoodsDTO goods);

	public GoodsDTO findById(Long goodsId);
	
	public List<GoodsDTO> findAll();
	
	public void updateAllColumn(GoodsDTO goods);

	public void delete(Long goodsId);

	public void updateGoodsStock(GoodsDTO goods, int quantity);
	
	public void updateGoodsSales(GoodsDTO goods, int quantity);

	public void updateGoodsScore(Long goodsId, Double rating);
	
	public void update(GoodsDTO goods);

}
