package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MenuDao;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.service.admin.MenuService;

/** 
 * 菜单管理实现类
* @author  作者: lubingliang: 
* @version 创建时间：2020年3月14日 上午9:04:09 
*/
@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;

	public int add(Menu menu) {
		
		return menuDao.add(menu);
	}

	public List<Menu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.findList(queryMap);
	}

	public List<Menu> findTopList() {
		// TODO Auto-generated method stub
		return menuDao.findTopList();
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.getTotal(queryMap);
	}

	public int edit(Menu menu) {
		// TODO Auto-generated method stub
		return   menuDao.edit(menu);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return menuDao.delete(id);
	}

	public List<Menu> findChildernList(Long parentId) {
		// TODO Auto-generated method stub
		return menuDao.findChildernList(parentId);
	}
	

}
