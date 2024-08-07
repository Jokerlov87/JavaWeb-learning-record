package com.creeper.pojo;

import com.creeper.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储用户的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;//用户的唯一标识

    private String userName;//用户名，不存在重复的用户名

    private String password;//密码


    private Role role;//角色身份
}

