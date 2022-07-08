package com.lens.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lens.config.redis.RedisService;
import com.lens.entity.User;
import com.lens.utils.JwtUtils;
import com.lens.utils.LoginResult;
import com.lens.utils.ResultCode;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * 登录成功处理器类
 */
@Component
public class LoginSuccessHandler  implements AuthenticationSuccessHandler {
    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置客户端的响应的内容类型8
        response.setContentType("application/json;charset=UTF-8");
        //获取当登录用户信息
        User user=(User)  authentication.getPrincipal();

        //生成token
        String token=jwtUtils.generateToken(user);
        //设置token签名密钥及过期时间
        long expireTime= Jwts.parser() //获取DefaultJwtParset对象
                .setSigningKey(jwtUtils.getSecret()) //设置签名的密钥
                .parseClaimsJws(token.replace("jwt_",""))
                .getBody().getExpiration().getTime(); //获取token过期时间
        //创建登录结果对象
        LoginResult loginResult=new LoginResult(user.getId(), ResultCode.SUCCESS,token,expireTime);
        //清除循环引用
        String result= JSON.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);

        //获取输出流
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        //把生成的token存在redis
        String tokenKey="token_"+token;
        redisService.set(tokenKey,token,jwtUtils.getExpiration()/1000);
    }
}
