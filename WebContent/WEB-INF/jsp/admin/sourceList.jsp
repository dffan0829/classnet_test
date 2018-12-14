<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--资源管理</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function selectAll(obj){
	var array = document.getElementsByName("sourceId");
	if(array){
		for(var i=0;i<array.length;i++){
			if(obj.checked){
				array[i].checked=true;
			}
			else{
				array[i].checked=false;
			}
		}
	}
}
function del(id){
	if(id!=0){
		location='<c:url value="/admin/source/source.do?m=del&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("sourceId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/source/source.do?m=del&ids="/>'+idstr;
		}
		else{
			alert("您还未选择");
		}
	}
}
function changeMenu(mid){
	var url = '<c:url value="/admin/source/source.do?m=list"/>';
	if(mid!=0){
		url += '&menuId='+mid;
	}
	location = url;
}
function search(){
	var url = '<c:url value="/admin/source/source.do?m=list"/>';
	var mid = document.getElementById("menuId");
	if(mid.value!=0){
		url += '&menuId='+mid.value;
	}
	var key = document.getElementById("key");
	if(key.value!=""){
		url += '&key='+key.value;
	}
	location = url;
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
	<div class="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
			<tr>
				<td height="30" bgcolor="#ffffff" align="left" style="padding-left:10px;">
					选择目录:
					<select name="menuId" id="menuId" onchange="changeMenu(this.value)">
						<option value="0">全部</option>
						<c:forEach items="${menuList}" var="menu">
							<option value="${menu.id}" ${param.menuId==menu.id?'selected=selected':''}><c:out value="${menu.name}"/></option>
						</c:forEach>
					</select>&nbsp;&nbsp;
					关键字:
					<input type="text" name="key" id="key" value="${key}"/>
					&nbsp;&nbsp;
					<input type="button" value="查询" onclick="search()"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="添加" onclick="location='<c:url value="/admin/source/source.do?m=add"/>';"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="c01">
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="10%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">ID</div></td>
	      <td width="45%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">名称</div></td>
	      <td width="15%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">目录</div></td>
	      <td width="15%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">文件大小</div></td>
	      <td width="15%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">操作选项 </div></td>
	    </tr>
	    <c:choose>
	    	<c:when test="${not empty sourceList}">
	    		<c:forEach items="${sourceList}" var="source" varStatus="vs">
	    		<tr>
			      <td height="30" bgcolor="#FFFFFF" align="center">
			      	<input type="checkbox" name="sourceId" value="${source.id}"/>&nbsp;<c:out value="${vs.index+1}" />
			       </td>
			      <td height="30" bgcolor="#FFFFFF" align="center" id="menu${source.id}">
			      	<a href="<f:message key="upload_http_path"/>/files/${source.filename}"><c:out value="${source.name}" /></a>
			      </td>
			      <td height="30" bgcolor="#FFFFFF" align="center" id="menu${source.id}">
			      	<c:out value="${source.sourceMenu.name}" />
			      </td>
			       <td height="30" bgcolor="#FFFFFF" align="center" id="menu${source.id}">
			      	<c:out value="${source.fileSize}" />
			      </td>
			      <td height="30" bgcolor="#FFFFFF" align="center">
			      	<a href="<c:url value="/admin/source/source.do?m=edit&id=${source.id}"/>">修改</a> 
			      	<a href="javascript:del(${source.id})">删除</a>
			     </td>
			    </tr>
	    		</c:forEach>
	    	</c:when>
	    	<c:otherwise>
	    		<tr>
			      <td colspan="4" align="center" height="30" bgcolor="#ffffff">
					暂无记录
			     </td>
			    </tr>
	    	</c:otherwise>
	    </c:choose>
	  </table>
	  <p><input type="checkbox" onclick="selectAll(this)"/> 全选 <input type="button" value="删除" class="shanchu" onclick="del(0)"/></p>
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
