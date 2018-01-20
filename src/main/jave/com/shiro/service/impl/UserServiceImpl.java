package com.shiro.service.impl;

import com.shiro.domain.User;
import com.shiro.mapper.UserMapper;
import com.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * UserService实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	public User getByUsername(String username) throws Exception {
		return userMapper.getByUsername(username);
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
