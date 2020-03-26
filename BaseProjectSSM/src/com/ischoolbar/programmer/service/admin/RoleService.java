package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Role;



/** 角色role  service
* @author  作者: lubingliang: 
* @version 创建时间：2020年3月23日 下午8:51:59 
*/
@Service
public interface RoleService {
	
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public List<Role> findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String, Object> queryMap);
}
