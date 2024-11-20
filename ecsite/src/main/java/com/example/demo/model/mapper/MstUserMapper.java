package com.example.demo.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.domain.MstUser;
import com.example.demo.model.form.LoginForm;

@Mapper
public interface MstUserMapper {
	@Select (value="SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}") 
	MstUser findByUserNameAndPassword (LoginForm form);
}
