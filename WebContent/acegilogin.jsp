<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
	<title>用户登录</title>
	<link href="<%=request.getContextPath()%>/css/default.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/prompts.css"
		rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="wrap">
			<!--头部定义开始-->
			<div id="header" class="main">
				<jsp:include page="/WEB-INF/jsp/pub/header.jsp"></jsp:include>
			</div>
			<!--头部定义结束-->
			<div id="center_all" class="main">
				<div class="u_management_bg">
				    <form action="<c:url value='j_acegi_security_check'/>" method="post">
				      <table width="70%" align="right" style="line-height: 30px;">
				        <c:if test="${not empty param.login_error}">
						<tr>
							<td colspan="2" align="left">
								<font color="red" > 用户名或密码错误</font>
							</td>	
						</tr>
						</c:if>
				        <tr><td width="50">用户名:</td><td><input type='text' name='j_username' <c:if test="${not empty param.login_error}">value='<c:out value="${ACEGI_SECURITY_LAST_USERNAME}"/>'</c:if> /></td></tr>
				        <tr><td>密码:</td><td><input type='password' name='j_password' /></td></tr>
				        <tr>
				        	<td colspan='2'>
				        		<input name="submit" type="submit" value="登录" />
				        		<input name="reset" type="reset" />
				        	</td>
				        </tr>
				      </table>
				    </form>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
