package com.example.demo.mapper;

import com.example.demo.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	void insertUser(User user);
	User getUserByUserNo(int userNo);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	void updateUser(User user);

}
