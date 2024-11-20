package com.example.demo.model.domain;

import java.sql.Timestamp; 

 public class TblPurchase {
      
	  private int id;
      
      private int userId;
      
      private int goodsId;
      
      private String goodsName;
      
      private int itemCount;
      
      private int total;
      
      private Timestamp createdAt;
    
  
    public int getId() {
       return  id;
    }
    public void setId(int id) {
    this.id = id;
    }
    
    public int getUserId() {
    return userId ;
    }
    
    public void setUserId(int userId){
    this.userId = userId;
    }
    
    public int getGoodsId(){
    return goodsId;
    }

    public void setGoodsId(int goodsld){
    this.goodsId = goodsld;
    }

    public String getGoodsName() {
    return goodsName;
    }


    public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
    }

    public int getltemCount() {
    return itemCount;
    }

    public void setltemCount(int itemCount){
    this.itemCount = itemCount ;
    }
    public int getTotal(){
    return total;
    }
 
    public void setTotal(int total){
    this.total = total;
    }

    public Timestamp getCreatedAt(){
    return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
    }
  }