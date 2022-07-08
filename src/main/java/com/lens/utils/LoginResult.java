package com.lens.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    //用户编号
    private Long id;

    //状态码
    private int code;

    //token令牌
    private String token;

    //token过期时间
    private long expireTime;
}
