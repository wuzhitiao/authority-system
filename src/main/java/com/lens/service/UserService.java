package com.lens.service;

import com.lens.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzt
 * @since 2022-06-28
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     */
    User findUserByUserName(String userName);

}
