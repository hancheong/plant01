package com.example.plant01.store;

public class StoreGoods {
    String goodsImg, StoreName, GoodsTitle, GoodsReview, GoodsPrice;

//    public StoreGoods(String storeName, String goodsTitle, String goodsReview, String goodsPrice){}
    public StoreGoods(String s){}

    public StoreGoods(String goodsImg, String StoreName, String GoodsTitle, String GoodsReview, String GoodsPrice) {
        this.goodsImg = goodsImg;
        this.StoreName = StoreName;
        this.GoodsTitle = GoodsTitle;
        this.GoodsReview = GoodsReview;
        this.GoodsPrice = GoodsPrice;
    }
    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsPrice) {
        this.goodsImg = goodsImg;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        this.StoreName = storeName;
    }

    public String getGoodsTitle() {
        return GoodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.GoodsTitle = goodsTitle;
    }

    public String getGoodsReview() {
        return GoodsReview;
    }

    public void setGoodsReview(String goodsReview) {
        this.GoodsReview = goodsReview;
    }

    public String getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.GoodsPrice = goodsPrice;
    }

}
