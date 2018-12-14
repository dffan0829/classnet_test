<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.classnet.util.page.WebUtil"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<div class="left_box">
<dl>
<dt><em>用户中心</em></dt>
<dd>
<div id="Con_11" >
<form action="<c:url value='/j_acegi_security_check'/>" method="post" style="margin:0">
	<div id="LoginFrom" class="Login_ajax">
	<ul style="line-height:30px;">
		<% 
			if(WebUtil.getLoginUser()!=null){
		%>
		<li>欢迎您:</li>
			<li style="padding-left:20px;"><%=WebUtil.getLoginUser() %></li>
			<li style="text-align:center">
				<% 
					if(WebUtil.isAdmin()){
				%>
				<a href="<c:url value="/admin"/>">进入后台管理</a>
				<%
					}
					else{
				%>
				<a href="<c:url value="/master/"/>">进入用户中心</a>
				<%		
					}
				%>
				&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value="/logout"/>">退出登录</a>
		</li>
		<%
			}
			else{
		%>
				<li>用户名：<input name="j_username" type="text" class="inputtext" style="width: 125px;" /></li>
			    <li>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="j_password" type="password" class="inputtext" style="width: 125px;" /></li>
				<li id="loginButton">
					<a href="<c:url value="/reg.do?m=toreg"/>">注册</a>&nbsp;&nbsp;
					<input type="submit" value="登　录" class="inputbutton" />
				</li>	
		<%
			}
		%>
	</ul>
	</div>
</form>
</div>
</dd>
</dl>
</div>
<script type="text/javascript">
function changeimagekey(){
	document.getElementById("imagekey").src="<c:url value="/imagekey.jsp?d="/>"+new Date().getTime();
}
</script>