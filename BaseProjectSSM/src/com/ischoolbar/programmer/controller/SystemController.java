package com.ischoolbar.programmer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ϵͳ�����������
 * 
 * @author LBL
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public String index() {
		return "index";
	}

}
