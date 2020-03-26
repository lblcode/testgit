package com.ischoolbar.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.User;

/**
 * user用户service
 * @author LBL
 *
 */
@Service
public interface UserService {
   
	public User  findByUsername(String userName);
	
}
