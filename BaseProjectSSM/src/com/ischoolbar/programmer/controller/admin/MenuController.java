package com.ischoolbar.programmer.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.MenuService;

/**
 * 菜单管理控制器
 * @author LBL
 *
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	private File[] listFiles;
	
	/**
	 * 菜单管理列表页
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mode){
		mode.addObject("topList",menuService.findTopList());
		mode.setViewName("menu/list");
		return mode;
	}
	/**
	 * 获取菜单列表
	 * @return
	 */
	@RequestMapping(value = "/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,
			@RequestParam(value="name",required=false,defaultValue="") String name
			
			){
		Map<String, Object> ret  = new HashMap<String, Object>();
		Map<String, Object> queryMap  = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("offset", page.getRows());
		queryMap.put("name", name);
		List<Menu> findList=menuService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", menuService.getTotal(queryMap));
		return ret;
	}
	/**
	 * 获取指定目录下的系统icon
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get_icons",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_iconsList(HttpServletRequest request){
		Map<String, Object> ret  = new HashMap<String, Object>();
		String realPath=request.getServletContext().getRealPath("/");
		File file = new File(realPath+"\\resources\\admin\\easyui\\css\\icons");
		List<String> icons = new ArrayList<String>();
		if(!	file.exists()){
			 ret.put("type", "error");
			 ret.put("msg", "文件目录不存在");
		}
		File[] listFiles = file.listFiles();
		for(File f : listFiles){
			if(f != null && f.getName().contains("png")){
				icons.add("icon-"+f.getName().substring(0,f.getName().indexOf(".")).replace("_", "-"));
			}
		}
		 ret.put("type", "success");
		 ret.put("content", icons);
		return ret;
	}
	
	/**
	 * 菜单添加
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	@ResponseBody// 把返回数据转成json字符串
	public Map<String ,String> add(Menu menu){
		
		 Map<String, String> ret = new HashMap<String, String>();
		 if(menu == null){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的菜单信息");
			 return ret;
		 }
		 if(StringUtils.isEmpty(menu.getName())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的菜单名称");
			 return ret;
		 }
		 if(StringUtils.isEmpty(menu.getIcon())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的菜单图标");
			 return ret;
		 }
		 if(menu.getParentId() == null){
			menu.setParentId(0l);
		 }
		 
		 if(menuService.add(menu)<= 0){
			 ret.put("type", "error");
			 ret.put("msg", "添加失败，请联系管理员");
			 return ret;
		 }
		 ret.put("type", "success");
		 ret.put("msg", "添加成功！");
		return ret;
	}
	/**
	 * 菜单修改
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "/edit",method=RequestMethod.POST)
	@ResponseBody// 把返回数据转成json字符串
	public Map<String ,String> edit(Menu menu){
		Map<String, String> ret = new HashMap<String, String>();
		 if(menu == null){
			 ret.put("type", "error");
			 ret.put("msg", "请选择正确的菜单信息");
			 return ret;
		 }
		 if(StringUtils.isEmpty(menu.getName())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的菜单名称");
			 return ret;
		 }
		 if(StringUtils.isEmpty(menu.getIcon())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入正确的菜单图标");
			 return ret;
		 }
		 if(menu.getParentId() == null){
			menu.setParentId(0l);
		 }
		 
		 if(menuService.edit(menu)<= 0){
			 ret.put("type", "error");
			 ret.put("msg", "修改失败，请联系管理员");
			 return ret;
		 }
		 ret.put("type", "success");
		 ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * 删除菜单信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody// 把返回数据转成json字符串
	public  Map<String, String> delete(
			@RequestParam(value="id",required=true,defaultValue="") Long id){
			Map<String, String> ret = new HashMap<String, String>();
			 if(id == null){
				 ret.put("type", "error");
				 ret.put("msg", "请选择菜单");
				 return ret;
			 }
			 
			List<Menu> fList= menuService.findChildernList(id);
			if(fList != null  && fList.size()>0){
				// 表示存在子分类
				 ret.put("type", "error");
				 ret.put("msg", "该菜单下有子分类不能删除");
				 return ret;
			}
			
			 if(menuService.delete(id) <= 0){
				 ret.put("type", "error");
				 ret.put("msg", "删除失败，联管理员");
				 return ret;
			 }
			 ret.put("type", "success");
			 ret.put("msg", "删除成功！");
			 return ret;
	}

}
