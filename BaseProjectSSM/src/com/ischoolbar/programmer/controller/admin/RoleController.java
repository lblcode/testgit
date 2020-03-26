package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.alibaba.druid.util.StringUtils;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.entity.admin.Role;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.RoleService;

/** 角色role控制器
* @author  作者: lubingliang: 
* @version 创建时间：2020年3月23日 下午8:25:39 
*/
@RequestMapping("/admin/role")
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	/**
	 * 角色列表页面
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mode){
		mode.setViewName("role/list");
		return mode;
	}
	/**
	 * 获取角色列表
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(value="name",required=false,defaultValue="") String name
			){
		
		Map<String, Object> ret  = new HashMap<String, Object>();
		Map<String, Object> queryMap  = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		ret.put("rows",roleService.findlist(queryMap));
		ret.put("total", roleService.getTotal(queryMap));
		return ret;
	}
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Role role){
		Map<String, String> ret = new  HashMap<String, String>();
		if(ret == null){
			ret.put("type", "error");
			 ret.put("msg", "请输入正确的角色信息");
			 return ret;
		}
		if(StringUtils.isEmpty(role.getName())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的角色名称");
			 return ret;
		}
		if(roleService.add(role) <= 0){
			 ret.put("type", "error");
			 ret.put("msg", "添加角色失败，请联系管理员");
			 return ret;
		}
		 ret.put("type", "success");
		 ret.put("msg", "添加角色成功");
		return ret;
	}
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Role role){
		Map<String, String> ret = new  HashMap<String, String>();
		if(ret == null){
			ret.put("type", "error");
			 ret.put("msg", "请输入正确的角色信息");
			 return ret;
		}
		if(StringUtils.isEmpty(role.getName())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的角色名称");
			 return ret;
		}
		if(roleService.edit(role) <= 0){
			 ret.put("type", "error");
			 ret.put("msg", "修改角色失败，请联系管理员");
			 return ret;
		}
		 ret.put("type", "success");
		 ret.put("msg", "修改角色成功");
		return ret;
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new  HashMap<String, String>();
		if(id == null){
			 ret.put("type", "error");
			 ret.put("msg", "请选择需删除的角色");
			 return ret;
		}
		try {
			if(roleService.delete(id) <= 0){
				 ret.put("type", "error");
				 ret.put("msg", "删除角色失败，请联系管理员");
			}
		} catch (Exception e) {
			// TODO: handle exception
			 ret.put("type", "error");
			 ret.put("msg", "该角色下存在权限或者用户信息，不允许删除");
		}
		 ret.put("type", "success");
		 ret.put("msg", "删除角色成功");
		return ret;
	}
}
