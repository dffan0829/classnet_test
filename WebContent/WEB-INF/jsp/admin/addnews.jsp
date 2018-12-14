<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--<c:choose><c:when test="${empty newsEntity}">发布新闻</c:when><c:otherwise>编辑新闻</c:otherwise></c:choose></title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function trim(str){
	return str.replace(/^\s+|\s+$/,"");
}
function check(form){
	if(form.menuId.value==0){
		alert("您还未选择新闻栏目");
		return false;
	}
	if(trim(form.title.value)==""){
		alert("新闻标题不能为空");
		return false;
	}
	if(trim(form.author.value)==""){
		alert("作者不能为空");
		return false;
	}
	<c:if test="${not empty newsEntity}">
		form.action="<c:url value="/admin/news/news.do?m=doedit"/>";
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
<jsp:include page="pub/left_news.jsp"></jsp:include>
</td>
<td valign="top">
	<div class="right">
	<form action="<c:url value="/admin/news/pubnews.do?m=dopub"/>" method="post" onsubmit="return check(this)" enctype="multipart/form-data" style="margin:0">
	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d1d5d8">
	<tr>
	<td height="28" colspan="2" bgcolor="#c6dbf8" class="biaoti" style="padding:0;">
	<c:choose>
		<c:when test="${empty newsEntity}">新闻发布</c:when>
		<c:otherwise>新闻编辑</c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>新闻栏目：</td>
	<td height="28" bgcolor="#FFFFFF">
		<select class="sheng" name="menuId" id="menuId">
			<option value="0">选择新闻栏目</option>
			<c:forEach items="${menuList}" var="menu">
			<option value="${menu.id}" ${newsEntity.newsMenu.id==menu.id?'selected=selected':''}><c:out value="${menu.name}"/></option>
			</c:forEach>
		</select> 
		<p>选择新闻栏目</p>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>标题：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="title" id="title" size="60" value="${newsEntity.title}" maxlength="25"/><p>请输入新闻标题</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>作者：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="author" id="author" value="<c:out value="${newsEntity.author}" default="admin"/>" maxlength="10"/><p>请输入新闻作者</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">来源：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="source" value="${newsEntity.source}" maxlength="10"/><p>请输入新闻来源</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">封面图片：</td>
	<td height="28" bgcolor="#FFFFFF">
		<input type="file" name="imgFile" id="imgFile"/>
		<p>请上传新闻的封面图片,只能为jpg,gif格式的图片</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>新闻内容：</td>
	<td height="28" bgcolor="#FFFFFF">
		<textarea name="content"><c:out value="${newsEntity.content}" escapeXml="false"/></textarea>
		<p>请输入新闻内容</p></td>
	</tr>
	</table>
	<div class="anniu">
	<p><input type="submit" value="提交" class="fabu" /> <input type="reset" value="重置" class="fabu" /></p>
	<input type="hidden" name="id" value="${newsEntity.id}"/>
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
var oFCKeditor = new FCKeditor('content') ;
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
