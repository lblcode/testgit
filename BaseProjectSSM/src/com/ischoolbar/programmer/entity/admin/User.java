package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * 
 * @author LBL
 *
 */
@Component
public class User {

	private Long id;// 用户ID ,自增
	private String username;// 用户登陆名
	private String password;// 密码
	private String photo;// 头像照片地址
	private int sex;// 性别 0 ，代表未知，1 男，2 女
	private Integer age;// 年龄
	private String address;// 住址

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
