package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.RoleDao;
import com.ischoolbar.programmer.entity.admin.Role;
import com.ischoolbar.programmer.service.admin.RoleService;

/** Role 的实现类
* @author  作者: lubingliang: 
* @version 创建时间：2020年3月23日 下午8:56:56 
*/
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	public int add(Role role) {
		// TODO Auto-generated method stub
		return roleDao.add(role);
	}

	public int edit(Role role) {
		// TODO Auto-generated method stub
		return roleDao.edit(role);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return roleDao.delete(id);
	}

	public List<Role> findlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roleDao.findList(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roleDao.getTotal(queryMap);
	}

}
