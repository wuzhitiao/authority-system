package com.lens.controller;


import com.lens.service.UserService;
import com.lens.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzt
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/listAll")
    public Result listAll(){
        return  Result.ok(userService.list());
    }

}

