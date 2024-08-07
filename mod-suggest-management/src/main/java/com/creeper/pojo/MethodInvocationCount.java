package com.creeper.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MethodInvocationCount {
    private Integer id; //ID
    private String className; //操作类名
    private String methodName; //操作方法名
    private Integer invocationCount;//操作次数
}
