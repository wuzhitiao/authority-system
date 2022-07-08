package com.lens.dao;

import com.lens.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzt
 * @since 2022-06-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 根据用户id查询权限列表
     */
    List<Permission>  findPermissionListByUserId(Long userId);

}
