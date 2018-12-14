<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/user.css"
			rel="stylesheet" type="text/css" />
		<title>会员中心</title>
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
							<div class="r_navigation">
								您现在的位置：
								<a href="<c:url value="/"/>"><f:message key="site_name" />
								</a>&gt;&gt;
								<a href="<c:url value="/master/"/>">会员中心</a>
							</div>
							<div class="c_spacing"></div>
							<div class="u_form1">
								<div style="text-align: center">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr align="center">
											<td id="TabTitle0" class="titlemouseover">
												会员信息
											</td>
										</tr>
									</table>
									<table width="100%" border="0" cellpadding="5" cellspacing="1"
										class="border" style="text-align: left;line-height: 30px;">
										<tr>
											<td align="right" width="20%">
												用户名:
											</td>
											<td align="left">
												<c:out value="${userEntity.username}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												邮箱:
											</td>
											<td align="left">
												<c:out value="${userEntity.email}" />
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- 我的控制菜单开始 -->
					<div id="main_left">
						<dl>
							<dt></dt>
							<dd>
								<div id="mg_user_left">
									<ul>
										<li id="menu_1" >
											<a href=#>信息管理</a>
										</li>
									</ul>
								</div>
								<!-- 我的控制菜单开结束 -->
								<div id="mg_user_right">
									<jsp:include page="left.jsp"/>
								</div>
								<div class="clearbox"></div>
								<!-- 用户快捷导航结束 -->
							</dd>
						</dl>
						<div class="clearbox"></div>
					</div>
					<div class="clearbox"></div>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
