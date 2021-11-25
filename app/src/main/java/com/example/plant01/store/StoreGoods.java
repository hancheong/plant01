package com.example.plant01.store;

public class StoreGoods {
    String StoreName, GoodsTitle, GoodsReview, GoodsPrice, goodsImg;

    StoreGoods(){}

    public StoreGoods(String StoreName, String GoodsTitle, String GoodsReview, String GoodsPrice, String goodsImg) {
        this.StoreName = StoreName;
        this.GoodsTitle = GoodsTitle;
        this.GoodsReview = GoodsReview;
        this.GoodsPrice = GoodsPrice;
        this.goodsImg = goodsImg;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) { this.StoreName = StoreName; }

    public String getGoodsTitle() { return GoodsTitle; }

    public void setGoodsTitle(String GoodsTitle) { this.GoodsTitle = GoodsTitle; }

    public String getGoodsReview() {
        return GoodsReview;
    }

    public void setGoodsReview(String GoodsReview) { this.GoodsReview = GoodsReview; }

    public String getGoodsPrice() { return GoodsPrice; }

    public void setGoodsPrice(String GoodsPrice) { this.GoodsPrice = GoodsPrice; }

    public String getgoodsImg() { return goodsImg; }

    public void setgoodsImg(String goodsImg) { this.goodsImg = goodsImg; }


}
