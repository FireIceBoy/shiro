package com.shiro.service;

import com.shiro.domain.User;

public interface UserService {
    User getByUsername(String username) throws Exception;
}
