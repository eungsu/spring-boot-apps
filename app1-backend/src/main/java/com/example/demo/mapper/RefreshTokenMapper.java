package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.RefreshToken;

@Mapper
public interface RefreshTokenMapper {

	void insertRefreshToken(RefreshToken refreshToken);
	void deleteRefreshTokenByUserNo(int userNo);
	RefreshToken getRefreshTokenByToken(String token);
}
