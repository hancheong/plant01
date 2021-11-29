package com.example.plant01.store;

public class StoreGoods {
    String GoodsID, StoreName, GoodsTitle, GoodsReview, GoodsPrice, goodsImg;

    public StoreGoods(String GoodsID, String storeName, String goodsTitle, String goodsReview, String goodsPrice){}
//    public StoreGoods(){};

    public StoreGoods(String GoodsID, String StoreName, String GoodsTitle, String GoodsReview, String GoodsPrice, String goodsImg) {
        this.GoodsID = GoodsID;
        this.StoreName = StoreName;
        this.GoodsTitle = GoodsTitle;
        this.GoodsReview = GoodsReview;
        this.GoodsPrice = GoodsPrice;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        this.GoodsID = goodsID;
    }
}
