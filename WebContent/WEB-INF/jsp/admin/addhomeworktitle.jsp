<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--<c:choose><c:when test="${empty homeWorkTitleEntity}">添加作业题目</c:when><c:otherwise>编辑作业题目</c:otherwise></c:choose></title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function trim(str){
	return str.replace(/^\s+|\s+$/,"");
}
function check(form){
	if(trim(form.title.value)==""){
		alert("标题不能为空");
		return false;
	}
	<c:if test="${not empty homeWorkTitleEntity}">
		form.action="<c:url value="/admin/homework.do?m=doEditTitle"/>";
	</c:if>
	return true;
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
	<div class="right">
	<form action="<c:url value="/admin/homework.do?m=doAddTitle"/>" method="post" style="margin:0px;" onsubmit="return check(this)">
	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d1d5d8">
	<tr>
	<td height="28" colspan="2" bgcolor="#c6dbf8" class="biaoti" style="padding:0;">
	<c:choose>
		<c:when test="${empty homeWorkTitleEntity}">添加作业题目</c:when>
		<c:otherwise>编辑作业题目</c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>标题：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="title" id="title" size="60" value="${homeWorkTitleEntity.title}" maxlength="200"/><p>请输入标题</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">描述：</td>
	<td height="28" bgcolor="#FFFFFF">
		<textarea name="description"><c:out value="${homeWorkTitleEntity.description}" escapeXml="false"/></textarea>
		<p>请输入描述信息</p></td>
	</tr>
	</table>
	<div class="anniu">
	<p><input type="submit" value="提交" class="fabu" /> 
		<input type="button" value="返回" class="fabu" onclick="javascript:history.back(-1);"/></p>
	<input type="hidden" name="id" value="${homeWorkTitleEntity.id}"/>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
var oFCKeditor = new FCKeditor('description') ;
oFCKeditor.BasePath	= "${pageContext.request.contextPath}/fckeditor/";
oFCKeditor.Width="710";
oFCKeditor.Height="340";
oFCKeditor.Value="&nbsp;&nbsp;&nbsp;&nbsp;";
oFCKeditor.ToolbarSet="classnet";
oFCKeditor.Config["CustomConfigurationsPath"]="${pageContext.request.contextPath}/fckeditor/myconfig.js?d="+new Date().getTime();
oFCKeditor.ReplaceTextarea();
</script>
</body>
</html>
