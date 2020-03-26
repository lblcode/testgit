<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>SSM脚手架后台管理员登陆</title>
		<meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
		<meta name="author" content="Vincent Garreau" />
		<meta name="viewport"  content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" media="screen" href="../resources/admin/login/css/style.css">
		<link rel="stylesheet" type="text/css" href="../resources/admin/login/css/reset.css" />
</head>
<body>

	<div id="particles-js">
		<div class="login">
			<div class="login-top">登录</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/img/name.png" />
				</div>
				<div class="login-center-input">
					<input type="text" name="username" id="username"value="" placeholder="请输入您的用户名"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'" />
					<div class="login-center-input-text">用户名</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/img/password.png" />
				</div>
				<div class="login-center-input">
					<input type="password" name="password"  id="password"   value="" placeholder="请输入您的密码"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'" />
					<div class="login-center-input-text">密码</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/img/ym.png" />
				</div>
				<div class="login-center-input">
					<input style="width: 50%"type="text" name="cpacha"  id="cpacha"  value="" placeholder="验证码"
						onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的验证码'" />
					<div class="login-center-input-text">验证码</div>
					<img id="cpacha-img" title="点击切换验证码" style="cursor:pointer; " src="get_cpacha?vl=4&w=100&h=40" width="110px" height="30px"  onclick="changeCpacha()"/>
				</div>
			</div>
			<div class="login-button">登录</div>
		</div>
		<div class="sk-rotating-plane"></div>
	</div>

	<!-- scripts -->
	<script src="../resources/admin/login/js/particles.min.js"></script>
	<script src="../resources/admin/login/js/app.js"></script>
	<script src="../resources/admin/login/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		function hasClass(elem, cls) {
			cls = cls || '';
			if (cls.replace(/\s/g, '').length == 0)
				return false; //当cls没有参数时，返回false
			return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
		}

		function addClass(ele, cls) {
			if (!hasClass(ele, cls)) {
				ele.className = ele.className == '' ? cls : ele.className + ' '
						+ cls;
			}
		}

		function removeClass(ele, cls) {
			if (hasClass(ele, cls)) {
				var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '')
						+ ' ';
				while (newClass.indexOf(' ' + cls + ' ') >= 0) {
					newClass = newClass.replace(' ' + cls + ' ', ' ');
				}
				ele.className = newClass.replace(/^\s+|\s+$/g, '');
			}
		}
		
		function changeCpacha(){
			$("#cpacha-img").attr("src",'get_cpacha?vl=4&w=100&h=40&t='+new Date().getTime());
		}
		
		document.querySelector(".login-button").onclick = function() {
			
			var username = $("#username").val();
			var password = $("#password").val();
			var cpacha = $("#cpacha").val();
			
			if(username == '' || username == 'undefine'){
				alert("请输入用户名");
				return ;
			}
			if(password == '' || password == 'undefine'){
				alert("请输入密码");
				return ;
			}
			if(cpacha == '' || cpacha == 'undefine'){
				alert("请输入验证码");
				return ;
			}
			addClass(document.querySelector(".login"), "active")
			addClass(document.querySelector(".sk-rotating-plane"),"active")
			document.querySelector(".login").style.display = "none"
					$.ajax({
						url:'login',
						data:{username:username,password:password,cpacha:cpacha},
						type:'post',
						dataType:'json',
						success:function(data){
							if(data.type == 'success'){
								window.location = 'index';
							}else{
								removeClass(document.querySelector(".login"), "active")
								removeClass(document.querySelector(".sk-rotating-plane"),"active")
								document.querySelector(".login").style.display = "block"
								 alert(data.msg);
								changeCpacha(); 
							}
						}
					});
		}
	</script>
</body>
</html>