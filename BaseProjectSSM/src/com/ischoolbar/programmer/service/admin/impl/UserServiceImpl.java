package com.ischoolbar.programmer.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.UserDao;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.UserService;

/**
 * user用户serviceimpl
 * 
 * @author LBL
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User findByUsername(String userName) {

		return userDao.findByUsername(userName);
		
	}

}
