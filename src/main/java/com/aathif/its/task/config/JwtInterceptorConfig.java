package com.aathif.its.task.config;

import com.aathif.its.task.dto.RequestMetaDTO;
import com.aathif.its.task.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptorConfig extends HandlerInterceptorAdapter {
    @Autowired
    JwtUtil jwtUtil;
    RequestMetaDTO requestMetaDTO;

    public JwtInterceptorConfig(RequestMetaDTO requestMetaDTO) {
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        if (!(request.getRequestURI().contains("user/login") ||request.getRequestURI().contains("user/register"))) {
            Claims claims = jwtUtil.verifyToken(authorization);

            requestMetaDTO.setId(Long.valueOf(claims.getIssuer()));
            requestMetaDTO.setName(claims.get("name").toString());
            requestMetaDTO.setEmail(claims.get("email").toString());
            requestMetaDTO.setMobile(claims.get("mobile").toString());
        }
        return super.preHandle(request, response, handler);
    }
}
