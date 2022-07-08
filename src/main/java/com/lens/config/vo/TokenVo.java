package com.lens.config.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {

    //过期时候
    private  Long expireTime;

    //token
    private  String  token;
}
