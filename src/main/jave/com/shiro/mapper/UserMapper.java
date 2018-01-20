package com.shiro.mapper;

import com.shiro.domain.User;

/**
 * 定义用户接口
 */
public interface UserMapper {
    User getByUsername(String username);
}
