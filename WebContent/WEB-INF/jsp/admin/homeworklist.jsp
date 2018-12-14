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
		location='<c:url value="/admin/homework.do?m=del&id="/>'+id+"&titleId=<c:out value="${titleEntity.id}"/>";
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
			location='<c:url value="/admin/homework.do?m=del&ids="/>'+idstr+"&titleId=<c:out value="${titleEntity.id}"/>";;
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
		<table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
			<tr>
				<td height="30" bgcolor="#ffffff" align="left" style="padding-left:10px;font-weight: bold;">
					作业题目：<c:out value="${titleEntity.title}" />
				</td>
			</tr>
		</table>
	</div>
	<div class="middle">
		<form action="<c:url value="/admin/homework.do"/>" method="get" style="margin:0px;" onsubmit="return check(this)">
		<table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
			<tr>
				<td height="30" bgcolor="#ffffff" align="center">
				<input type="hidden" name="m" value="list"/>
				关键字:<input type="text" name="key" value="${key}"/>
				用户名:<input type="text" name="username" value="${username}"/>
				<input type="hidden" name="titleId" value="${titleEntity.id}" />
				<input type="submit" value="搜索"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div id="c01">
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="10%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">ID</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">用户名</div></td>
	      <td width="30%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">标题</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">提交时间</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">操作选项 </div></td>
	    </tr>
	    <c:choose>
	    	<c:when test="${not empty homeworkList}">
	    		<c:forEach items="${homeworkList}" var="hw" varStatus="vs">
	    		<tr>
			      <td height="30" bgcolor="#FFFFFF" align="center">
			      	<input type="checkbox" name="hwId" value="${hw.id}"/>&nbsp;<c:out value="${vs.index+1}" />
			       </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<c:out value="${hw.userEntity.username}" />
			      </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<c:out value="${hw.name}" />
			      </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<f:formatDate value="${hw.addtime}" pattern="yyyy-MM-dd HH:mm"/>
			      </td>
			      <td bgcolor="#FFFFFF" align="center">
			      	<a href="javascript:del(${hw.id})">删除</a>&nbsp;&nbsp;
			      	<a target="_blank" href="<c:url value="/admin/homework.do?m=download&id=${hw.id}"/>">下载</a>
			     </td>
			    </tr>
	    		</c:forEach>
	    	</c:when>
	    	<c:otherwise>
	    		<tr>
			      <td colspan="5" align="center" height="30" bgcolor="#ffffff">
					暂无记录
			     </td>
			    </tr>
	    	</c:otherwise>
	    </c:choose>
	  </table>
	  <p><input type="checkbox" onclick="selectAll(this)"/> 全选 <input type="button" value="删除" class="shanchu" onclick="del(0)"/>
	  	<input type="button" value="返回题目列表" onclick="location='<c:url value="/admin/homework.do?m=titleList"/>';"/>
	  </p>
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
