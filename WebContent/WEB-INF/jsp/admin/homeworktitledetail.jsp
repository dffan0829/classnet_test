<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--作业题目</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
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
	<div class="right">
	<form action="<c:url value="/admin/homework.do?m=doAddTitle"/>" method="post" style="margin:0px;" onsubmit="return check(this)">
	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d1d5d8">
	<tr>
	<td height="28" colspan="2" bgcolor="#c6dbf8" class="biaoti" style="padding:0;">
		作业题目
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>标题：</td>
	<td height="28" bgcolor="#FFFFFF"><c:out value="${homeWorkTitleEntity.title}" /></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">描述：</td>
	<td height="28" bgcolor="#FFFFFF">
		<c:out value="${homeWorkTitleEntity.description}" escapeXml="false"/>	
	</td>
	</tr>
	</table>
	<div class="anniu">
	<p><input type="button" value="返回" class="fabu" onclick="javascript:history.back(-1);"/></p>
	</div>
	</form>
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
