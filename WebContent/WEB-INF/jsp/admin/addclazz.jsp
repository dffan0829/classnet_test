<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--<c:choose><c:when test="${empty clazzEntity}">发布课程</c:when><c:otherwise>编辑课程</c:otherwise></c:choose></title>
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
	if(trim(form.author.value)==""){
		alert("作者不能为空");
		return false;
	}
	if(form.filetypeId.value==0){
		alert("文件类型不能为空");
		return false;
	}
	<c:if test="${empty clazzEntity}">
	if(trim(form.file.value)==""){
		alert("课程文件不能为空");
		return false;
	}
	if(trim(form.flashFile.value)==""){
		alert("FLASH文件不能为空");
		return false;
	}
	</c:if>
	<c:if test="${not empty clazzEntity}">
		form.action="<c:url value="/admin/clazz/clazz.do?m=doedit"/>";
	</c:if>
	return true;
}
function changeMenu(pid){
	$("#menuId").empty();
	if(pid==0){
		return ;
	}
	$.ajax({
		type:"get",
		url:"<c:url value="/changeMenu.do"/>",
		data:"pid="+pid,
		success:function(obj){
			if(obj){
				var array = eval("("+obj+")");
				if(array){
					for(var i=0;i<array.length;i++){
						$("#menuId").append('<option value="'+array[i].id+'">'+array[i].name+'</option>');
					}
				}
			}
		}
	});
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
<jsp:include page="pub/left_clazz.jsp"></jsp:include>
</td>
<td valign="top">
	<div class="right">
	<form action="<c:url value="/admin/clazz/add.do?m=doadd"/>" method="post" onsubmit="return check(this)" enctype="multipart/form-data" style="margin:0">
	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d1d5d8">
	<tr>
	<td height="28" colspan="2" bgcolor="#c6dbf8" class="biaoti" style="padding:0;">
	<c:choose>
		<c:when test="${empty clazzEntity}">添加课程</c:when>
		<c:otherwise>课程编辑</c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>课程栏目：</td>
	<td height="28" bgcolor="#FFFFFF">
		<select class="sheng" name="parentMenuId" id="parentMenuId" onchange="changeMenu(this.value)">
			<option value="0">选择课程栏目</option>
			<c:forEach items="${menuList}" var="menu">
			<option value="${menu.id}" ${clazzEntity.clazzMenu.parentMenuEntity.id==menu.id?'selected=selected':''}><c:out value="${menu.name}"/></option>
			</c:forEach>
		</select> 
		<select name="menuId" id="menuId" class="sheng">
			<c:if test="${not empty clildMenuList}">
				<c:forEach items="${clildMenuList}" var="cm">
					<option value="${cm.id}" ${cm.id==clazzEntity.clazzMenu.id?'selected=selected':''}><c:out value="${cm.name}" /></option>
				</c:forEach>
			</c:if>
		</select>
		<p>选择新闻栏目</p>
	</td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>标题：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="name" id="name" size="60" value="${clazzEntity.name}" maxlength="25"/><p>请输入课程名称</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>作者：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="author" id="author" value="<c:out value="${clazzEntity.author}" default="admin"/>" maxlength="10"/><p>请输入课程作者</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">作者单位：</td>
	<td height="28" bgcolor="#FFFFFF"><input type="text" name="school" value="${clazzEntity.school}" maxlength="10"/><p>请输入作者单位</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7">封面图片：</td>
	<td height="28" bgcolor="#FFFFFF">
		<input type="file" name="imgFile" id="imgFile"/>
		<p>请上传封面图片,只能为jpg,gif格式的图片</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><span>*</span>文件类型：</td>
	<td height="28" bgcolor="#FFFFFF">
		<select name="filetypeId" id="filetypeId">
			<option value="0">请选择文件类型</option>
			<c:forEach items="${fileTypeList}" var="ft">
				<option value="${ft.id}" ${clazzEntity.fileType.id==ft.id?'selected=selected':''}><c:out value="${ft.name}"/></option>
			</c:forEach>
		</select>
		<p>请选择文件类型</p></td>
	</tr>
	<tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><c:if test="${empty clazzEntity}"><span>*</span></c:if>课程文件：</td>
	<td height="28" bgcolor="#FFFFFF">
		<input type="file" name="file" id="file"/>
		<p>请上传课程文件,只能为doc,xls,ppt,txt格式的,大小不能超过5M</p></td>
	</tr>
	<tr>
	<td width="15%" height="28" align="right" bgcolor="#edf1f7"><c:if test="${empty clazzEntity}"><span>*</span></c:if>FLASH文件：</td>
	<td height="28" bgcolor="#FFFFFF">
		<input type="file" name="flashFile" id="flashFile"/>
		<p>请上传课程文件Flash,swf格式,大小不能超过5M</p></td>
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
