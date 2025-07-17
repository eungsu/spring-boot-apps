package com.example.app1.mapper;

import com.example.app1.vo.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {

  void insertRefreshToken(RefreshToken refreshToken);
  void deleteRefreshTokenByUserNo(int userNo);
  RefreshToken getRefreshTokenByToken(String token);
}
