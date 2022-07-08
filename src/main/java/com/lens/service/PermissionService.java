package com.lens.service;

import com.lens.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzt
 * @since 2022-06-28
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据用户ID查询权限列表
     */
    List<Permission> findPermissionListByUserId(Long userId);
}
