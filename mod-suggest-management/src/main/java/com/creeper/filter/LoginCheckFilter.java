//package com.creeper.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.creeper.pojo.Result;
//import com.creeper.utils.JwtUtils;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class LoginCheckFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("Filter initialized");
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest req=(HttpServletRequest) request;
//        HttpServletResponse resp=(HttpServletResponse) response;
//
//        log.info("进入了");
//
//        //获取请求url
//        String url = req.getRequestURI().toString();
//        log.info("请求的url：{}",url);
//
//        //判断请求url中是否包含login，如果包含，说明是登录操作，放行。
//        if(url.contains("login")) {
//            log.info("登陆操作，放行");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //获取请求头中的令牌（token）
//        String jwt=req.getHeader("token");
//
//        //判断令牌是否存在，如果并不存在，返回错误结果（未登录）
//        if(StringUtils.hasLength(jwt)){
//            log.info("请求头token为空，返回未登录的信息");
//            Result error=Result.error("NOT_LOGIN");
//            //手动转换 对象--json
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return;
//        }
//
//        //解析token，如果解析失败，返回错误结果（未登录）
//        try{
//            JwtUtils.parseToken(jwt);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.info("解析令牌失败，返回未登录错误信息");
//            Result error =Result.error("NOT_LOGIN");
//            //手动转换 对象--json
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return;
//        }
//
//        //放行
//        log.info("令牌合法，放行");
//        filterChain.doFilter(request,response);
//    }
//
//    @Override
//    public void destroy() {
//        log.info("Filter destroyed");
//    }
//
//}
