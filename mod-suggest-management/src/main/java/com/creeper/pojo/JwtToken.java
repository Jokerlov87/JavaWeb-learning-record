package com.creeper.pojo;

import com.creeper.enums.Role;
import lombok.Data;

import java.util.Date;

/**
 * JwtToken 类表示 JSON Web Tokens 的数据模型
 * 用于存储 JWT 相关的信息
 */
@Data
public class JwtToken {
    private String id;//令牌的唯一标识符

    private String token;//token字符串

    private Date createdDate;//令牌的创建日期，在插入时由数据库自动填充为当前时间

    private Date expirationDate;//令牌的过期日期

    private String userId;//与令牌关联的用户ID

    private Role role;//与令牌关联的用户角色身份
}