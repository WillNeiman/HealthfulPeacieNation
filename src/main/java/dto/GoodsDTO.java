package dto;

public class GoodsDTO {

	private long goodsId;
	
	private String goodsName;
	
	private int goodsCost;
	
	private int goodsStock;
	
	private double goodsScore;
	
	private String goodsThumbnail;

	private String goodsImage1;
	
	private String goodsImage2;
	
	private String goodsDescription;
	
	private int goodsSales;
	
	private String goodsCategory;

	public GoodsDTO(long goodsId, String goodsName, int goodsCost, int goodsStock, double goodsScore, String goodsThumbnail,
			String goodsImage1, String goodsImage2, String goodsDescription, int goodsSales) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsCost = goodsCost;
		this.goodsStock = goodsStock;
		this.goodsScore = goodsScore;
		this.goodsThumbnail = goodsThumbnail;
		this.goodsImage1 = goodsImage1;
		this.goodsImage2 = goodsImage2;
		this.goodsDescription = goodsDescription.replace(System.lineSeparator(), "<br>"); 
		this.goodsSales = goodsSales;
	}
	
	public GoodsDTO(long goodsId, String goodsName, int goodsCost, String goodsThumbnail, String goodsImage1, String goodsImage2, String goodsDescription) {
	      super();
	      this.goodsId = goodsId;
	      this.goodsName = goodsName;
	      this.goodsCost = goodsCost;
	      this.goodsThumbnail = goodsThumbnail;
	      this.goodsImage1 = goodsImage1;
	      this.goodsImage2 = goodsImage2;
	      this.goodsDescription = goodsDescription.replace(System.lineSeparator(), "<br>");
	   }


	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(int goodsCost) {
		this.goodsCost = goodsCost;
	}

	public int getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}

	public double getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(double goodsScore) {
		this.goodsScore = goodsScore;
	}

	public String getThumbnail() {
		return goodsThumbnail;
	}

	public void setThumbnail(String goodsThumbnail) {
		this.goodsThumbnail = goodsThumbnail;
	}

	public String getGoodsImage1() {
		return goodsImage1;
	}

	public void setGoodsImage1(String goodsImage1) {
		this.goodsImage1 = goodsImage1;
	}

	public String getGoodsImage2() {
		return goodsImage2;
	}

	public void setGoodsImage2(String goodsImage2) {
		this.goodsImage2 = goodsImage2;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public int getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(int goodsSales) {
		this.goodsSales = goodsSales;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	
	
	
}
