<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--作业管理</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function selectAll(obj){
	var array = document.getElementsByName("hwId");
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
function check(form){
	if(form.key.value==""){
		alert("不能为空");
		return false;
	}
	return true;
}
function del(id){
	if(id!=0){
		location='<c:url value="/admin/homework.do?m=delTitle&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("hwId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/homework.do?m=delTitle&ids="/>'+idstr;
		}
		else{
			alert("您还未选择");
		}
	}
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
	<div class="middle">
		<form action="<c:url value="/admin/homework.do"/>" method="get" style="margin:0px;" onsubmit="return check(this)">
		<table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
			<tr>
				<td height="30" bgcolor="#ffffff" align="center">
				<input type="hidden" name="m" value="titleList"/>
				题目关键字:<input type="text" name="key" value="${key}"/>
				<input type="submit" value="搜索"/>
				<input type="button" value="添加题目" onclick="location='<c:url value="/admin/homework.do?m=addTitle"/>'"/>		
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div id="c01">
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="10%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">ID</div></td>
	      <td width="50%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">题目名称</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">日期</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">操作选项 </div></td>
	    </tr>
	    <c:choose>
	    	<c:when test="${not empty homeworktitleList}">
	    		<c:forEach items="${homeworktitleList}" var="hw" varStatus="vs">
	    		<tr>
			      <td height="30" bgcolor="#FFFFFF" align="center">
			      	<input type="checkbox" name="hwId" value="${hw.id}"/>&nbsp;<c:out value="${vs.index+1}" />
			       </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<a href="<c:url value="/admin/homework.do?m=list&titleId=${hw.id}"/>"><c:out value="${hw.title}" /></a>
			      </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<f:formatDate value="${hw.time}" pattern="yyyy-MM-dd HH:mm"/>
			      </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<a href="<c:url value="/admin/homework.do?m=editTitle&id=${hw.id}"/>">修改</a>&nbsp;&nbsp;
			      	<a href="javascript:del(${hw.id})">删除</a>&nbsp;&nbsp;
			      	<a href="<c:url value="/admin/homework.do?m=titleDetail&id=${hw.id}"/>">详细</a>&nbsp;&nbsp;
			      	<a href="<c:url value="/admin/homework.do?m=list&titleId=${hw.id}"/>">查看作业</a>
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
