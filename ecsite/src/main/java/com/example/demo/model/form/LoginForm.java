package com.example.demo.model.form;

import java.io.Serializable;

public class LoginForm implements Serializable{
     private String userName;
     private String password;
     
     public String getUserNane(){
        return userName;
     }

     public void setUserName(String userName){
     this.userName = userName;
     }

     public String getPassword() {
          return password;
     }
   
     public void setPassword(String password) {
        this.password = password;
     }
}