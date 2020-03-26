package com.ischoolbar.programmer.dao.admin;
/**
 * user用户dao
 * @author LBL
 *
 */

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.User;

@Repository
public interface UserDao {

	public User findByUsername(String userName);
	
	
}
