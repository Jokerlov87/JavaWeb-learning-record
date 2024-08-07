package com.creeper.aspect;

import com.alibaba.fastjson.JSONObject;
import com.creeper.mapper.MethodInvocationCountMapper;
import com.creeper.mapper.OperateLogMapper;
import com.creeper.pojo.MethodInvocationCount;
import com.creeper.pojo.OperateLog;
import com.creeper.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private MethodInvocationCountMapper methodInvocationCountMapper;

    @Around("@annotation(com.creeper.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作时间
        LocalDateTime oprateTime =LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams=Arrays.toString((args));

        long begin =System.currentTimeMillis();
        //调用原始目标方法运行
        Object result=joinPoint.proceed();
        long end =System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime =end-begin;


        //记录操作日志
        OperateLog operateLog =new OperateLog(null,oprateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}",operateLog);

        // 更新方法调用统计
        updateMethodInvocationCount(className, methodName);


        return result;
    }
    private void updateMethodInvocationCount(String className, String methodName) {
        // 查询当前方法调用次数
        MethodInvocationCount count = methodInvocationCountMapper.findByClassNameAndMethodName(className, methodName);
        if (count == null) {
            // 如果统计表中没有该方法记录，则插入新记录
            count = new MethodInvocationCount(null, className, methodName, 1);
            methodInvocationCountMapper.insert(count);
        } else {
            // 否则更新调用次数
            count.setInvocationCount(count.getInvocationCount() + 1);
            methodInvocationCountMapper.updateInvocationCount(count);
        }
    }
}
