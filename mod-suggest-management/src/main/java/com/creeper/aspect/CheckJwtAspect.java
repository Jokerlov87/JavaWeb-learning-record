//package com.creeper.aspect;
//
//
//import com.creeper.security.JwtTokenProvider;
//import com.creeper.service.JwtTokenService;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//
///**
// * CheckJwtAspect 类是一个切面（Aspect），用于在调用AdminController中的方法前检查JWT令牌的有效性
// */
//@Aspect
//@Component
//public class CheckJwtAspect {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private JwtTokenService jwtTokenService;
//
//    /**
//     * 环绕通知方法，用于在目标方法执行前后执行额外的操作
//     * 这个方法会在所有匹配的方法执行之前运行，检查JWT令牌的有效性
//     *
//     * @param joinPoint 代表正在执行的方法调用
//     * @return 方法执行的结果或者错误响应
//     * @throws Throwable 如果方法执行过程中抛出异常
//     */
//    @Around("execution(* com.creeper.controller.SuggestController.*(..))")
//    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String token = request.getHeader("Authorization");
//
//        // 检查JWT令牌是否存在并且有效
//        if (token == null || !jwtTokenProvider.validateToken(token) || jwtTokenService.getAdminTokenByToken(token) == null) {
//            return ResponseEntity.status(401).body("Unauthorized access");
//        }
//
//        Claims claims = jwtTokenProvider.getClaims(token);
//
//        // 验证用户角色是否为ADMIN
//        if (claims.containsKey("role ") && "ADMIN".equals(claims.get("role "))) {
//            return joinPoint.proceed();
//        } else {
//            return ResponseEntity.status(403).body("Forbidden: ADMIN role required");
//        }
//    }
//}