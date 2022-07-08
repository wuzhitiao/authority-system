package com.lens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lens.entity.User;
import com.lens.dao.UserMapper;
import com.lens.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzt
 * @since 2022-06-28
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        //创建条件够构造器
        QueryWrapper<User>  queryWrapper=new QueryWrapper<>();
        //用户名
        queryWrapper.eq("username",userName);
        //返回查询条件
        return baseMapper.selectOne(queryWrapper);
    }
}
