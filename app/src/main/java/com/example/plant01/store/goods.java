package com.example.plant01.store;


import android.widget.EditText;

public class goods {  // 테스트 상품 페이지 테이블 클래스
    String name, title, review, price;

    public goods(EditText name, EditText title, EditText review, EditText price) {}

    public String getName() {return name;}

    public String getTitle() {return title;}

    public String getReview() {return review;}

    public String getPrice() {return price;}

    public void setName(String name) {this.name = name;}

}
