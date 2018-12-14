<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>会员注册</title>
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/article.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
		#regtable{line-height: 30px;}
		#regtable tr td{padding:4px;}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
		<script type="text/javascript">
		function checkUser(user){
			if(user==""){
				$("#usernamespan").html("用户名不能为空");
			}
			else{
				$.ajax({
					type:"get",
					url:"<c:url value="/reg.do?m=checkuser"/>",
					data:"username="+user,
					success:function(msg){
						if(msg=="1"){
							$("#usernamespan").html("");
							$("#userFlag").val("1");
						}
						else{
							$("#usernamespan").html("该用户名已经存在");
							$("#userFlag").val("2");
						}
					}
				});
			}
		}
		function check(){
			var num=0;
			if($("#username").val()==""){
				$("#usernamespan").html("用户名不能为空");
				num++;
			}
			else if($("#userFlag").val()=="2"){
				$("#usernamespan").html("该用户名已经存在");
				num++;
			}
			if($("#password").val()==""){
				$("#passwordspan").html("密码不能为空");
				num++;
			}
			if($("#password").val()!=$("#password1").val()){
				$("#password1span").html("两次密码输入不一样");
				num++;
			}
			if($("#email").val()==""){
				$("#emailspan").html("邮箱不能为空");
				num++;
			}
			else if(!/^([a-zA-Z0-9_])+@[a-zA-Z0-9_]+(\.([a-zA-Z0-9_]+))+$/.test($("#email").val())){
				$("#emailspan").html("邮箱格式不正确");
				num++;
			}
			if(num>0){
				return false;
			}
		}
		</script>
	</head>
	<body>
		<div class="wrap">
			<!--头部定义开始-->
			<div id="header" class="main">
				<jsp:include page="/WEB-INF/jsp/pub/header.jsp"></jsp:include>
			</div>
			<!--头部定义结束-->
			<div id="center_all" class="main">
				<div id="main_bg">
					<div id="main_right">
						<div id="main_right_box">
							<!-- 网站位置导航信息开始 -->
							<div class="r_navigation">
								您现在的位置：
								<a href="<c:url value="/"/>"><f:message key="site_name"/></a>&gt;&gt;用户注册
							</div>
							<!-- 网站位置导航信息结束 -->
							<div class="c_spacing"></div>
							<div class="r_ad">
								<img src="<f:message key="image_http_url"/>/images/rightAD1.jpg" border="0" />
							</div>
							<div class="c_spacing"></div>
							<div class="c_main_box">
								<div class="class_title">
									<span class="childclass_pic"></span>用户注册
								</div>
								<div class="class_content">
								<form action="<c:url value="/reg.do?m=doreg"/>" method="post" style="margin:0" onsubmit="return check()">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="regtable">
									<tr>
										<td width="60" align="right">用户名:</td>
										<td align="left">
											<input type="text" name="username" id="username" maxlength="16" onchange="checkUser(this.value)"/>
											<span style="color:red" id="usernamespan"></span>
											<input type="hidden" name="userFlag" id="userFlag" />
										</td>
									</tr>
									<tr>
										<td align="right">密码:</td>
										<td align="left"><input type="password" name="password" id="password" maxlength="16"/><span style="color:red" id="passwordspan"></span></td>
									</tr>
									<tr>
										<td align="right">重复密码:</td>
										<td align="left"><input type="password" name="password1" id="password1"/><span style="color:red" id="password1span"></span></td>
									</tr>
									<tr>
										<td align="right">邮箱:</td>
										<td align="left"><input type="text" name="email" id="email" maxlength="24"/><span style="color:red" id="emailspan"></span></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<span style="color:red">以上所有信息都必须先正确填写后才能继续下一步注册操作。</span>
											<br />
											<input type="submit" value="提交" />
										</td>
									</tr>		
								</table>
								</form>
								</div>
								<div class="class_bot"></div>
							</div>
						</div>
					</div>
					<div id="sideBar">
						<jsp:include page="/WEB-INF/jsp/userbox.jsp"></jsp:include>
					</div>
				</div>
			</div>
			<div class="clearbox"></div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
