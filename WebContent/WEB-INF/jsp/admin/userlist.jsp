<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--用户管理</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function search(){
	var url = "<c:url value="/admin/user.do?m=list"/>";
	var key = $("#key").val();
	if(key!=""){
		url+="&key="+key;
	}
	location=url;
}
</script>
</head>
<body>
<div class="content">
<jsp:include page="pub/top.jsp"></jsp:include>
<!--头部结束-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td class="left" valign="top">
<jsp:include page="pub/left.jsp"></jsp:include>
</td>
<td valign="top">
	<div id="c01">
	<dl>
	<dt>用户名:<input type="text" class="qidian" id="key" name="key" value="${key}"/> </dt>
	<dd><input type="button" value="查询" class="chaxun" onclick="search()"/></dd>
	</dl>
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">ID</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">用户名</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">密码</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">邮箱</td>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">状态</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">操作选项</td>
	    </tr>
		<c:choose>
		<c:when test="${not empty userList}">
		<c:forEach items="${userList}" var="user" varStatus="vs">
		<tr>
	      <td height="30" bgcolor="#FFFFFF" align="center">
	        <input type="checkbox" name="userId" value="${user.id}"/> 
	        <c:out value="${vs.index+1}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="left" style="padding-left:10px;"><c:out value="${user.username}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${user.password}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${user.email}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center">
	      	<c:choose>
	      		<c:when test="${user.enable}">正常</c:when>
	      		<c:otherwise><span style="color:red">停用</span></c:otherwise>
	      	</c:choose>
	      </td>
	      <td width="10%" height="30" bgcolor="#FFFFFF" align="center">
	      	<c:choose>
	      		<c:when test="${user.enable}">
	      		<a href="<c:url value="/admin/user.do?m=changestatus&id=${user.id}&status=0"/>">停用</a>&nbsp;
	      		恢复
	      		</c:when>
	      		<c:otherwise>
	      		停用&nbsp;
	      		<a href="<c:url value="/admin/user.do?m=changestatus&id=${user.id}&status=1"/>">恢复</a>
	      		</c:otherwise>
	      	</c:choose>
	      </td>
	    </tr>				
	    </c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
			<td colspan="6" height="30" bgcolor="#ffffff" align="center">暂无数据</td>
		</tr>	
		</c:otherwise>
		</c:choose>
	  </table>
	  <jsp:include page="/WEB-INF/jsp/pub/page.jsp"></jsp:include>
	</div>
	<br />
	<br />
</td>
</tr>
</table>
<jsp:include page="pub/foot.jsp"></jsp:include>
</div>
</body>
</html>
