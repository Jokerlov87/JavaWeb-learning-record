package com.creeper.mapper;

import com.creeper.pojo.JwtToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JwtTokenMapper {

    @Insert("INSERT INTO user_tokens (user_id, token, expirationDate) VALUES (#{userId}, #{token}, #{expirationDate})")
    void insertUserToken(JwtToken jwtToken);

    @Insert("INSERT INTO admin_tokens (user_id, token, expirationDate) VALUES (#{userId}, #{token}, #{expirationDate})")
    void insertAdminToken(JwtToken jwtToken);

    @Select("SELECT * FROM user_tokens WHERE token = #{token}")
    JwtToken getUserTokenByToken(String token);

    @Select("SELECT * FROM admin_tokens WHERE token = #{token}")
    JwtToken getAdminTokenByToken(String token);
}