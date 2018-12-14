<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--<c:choose><c:when test="${empty sourceEntity}">发布资源</c:when><c:otherwise>编辑资源</c:otherwise></c:choose></title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function trim(str){
	return str.replace(/^\s+|\s+$/,"");
}
function check(form){
	if(form.menuId.value==0){
		alert("您还未选择栏目");
		return false;
	}
	if(trim(form.name.value)==""){
		alert("标题不能为空");
		return false;
	}
	<c:if test="${empty clazzEntity}">
	if(form.file.value==""){
		alert("上传的资源文件不能为空");
		return false;
	}
	</c:if>
	<c:if test="${not empty clazzEntity}">
		form.action="<c:url value="/admin/source/source.do?m=doedit"/>";
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
<jsp:include page="pub/left_source.jsp"></jsp:include>
</td>
<td valign="top">
	<div class="right">
	<form action="<c:url value="/admin/source/source.do?m=doadd"/>" method="post" onsubmit="return check(this)" enctype="multipart/form-data" style="margin:0">
	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d1d5d8">
	<tr>
	<td height="28" colspan="2" bgcolor="#c6dbf8" class="biaoti" style="padding:0;">
	<c:choose>
		<c:when test="${empty sourceEntity}">添加资源</c:when>
		<c:otherwise>资源编辑</c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>选择目录：</td>
	<td height="28" bgcolor="#FFFFFF">
		<select name="menuId" id="menuId">
			<option value="0">请选择目录</option>
			<c:forEach items="${menuList}" var="menu">
				<option value="${menu.id}" ${sourceEntity.sourceMenu.id==menu.id?'selected=selected':''}><c:out value="${menu.name}"/></option>
			</c:forEach>
		</select>
		<p>请选择目录</p></td>
	</tr>
	<tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>标题：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="name" id="name" size="60" value="${sourceEntity.name}" /><p>请输入资源名称</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>文件大小：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="fileSize" id="fileSize" value="${sourceEntity.fileSize}" maxlength="10"/><p>请输入资源文件大小</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>上传文件：</td>
	<td height="28" bgcolor="#FFFFFF">
		<input type="file" name="file" id="file" />
		<p>请上传资源文件,只能为<span style="color:red">doc、xls、ppt、txt、rar、zip</span>格式</p>
	</td>
	</tr>
	</table>
	<div class="anniu">
	<p><input type="submit" value="提交" class="fabu" /> 
		<c:choose>
			<c:when test="${empty clazzEntity}"><input type="reset" value="重置" class="fabu" /></c:when>
			<c:otherwise><input type="button" value="返回" class="fabu" onclick="history.back()" /></c:otherwise>
		</c:choose>
	</p>
	<input type="hidden" name="id" value="${clazzEntity.id}"/>
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
