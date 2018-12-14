<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--文件类型</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function edit(id,name){
	$("#menu"+id).html('<input type="text" name="name'+id+'" id="name'+id+'" value="'+name+'"/>');
	$("#edit"+id).hide();
	$("#edit_submit"+id).show();
}
function edit_submit(id){
	if($("#name"+id).val()==""){
		alert("不能为空");
		return ;
	}
	$.ajax({
		type:"post",
		url:"<c:url value="/admin/clazz/fileType.do"/>",
		data:"m=edit&id="+id+"&name="+$("#name"+id).val(),
		success:function(obj){
			if(obj=="1"){
				$("#menu"+id).html($("#name"+id).val());
				alert("操作成功");
			}
			else{
				alert("操作失败");
			}
		}
	});
}
function selectAll(obj){
	var array = document.getElementsByName("menuId");
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
	if(form.name.value==""){
		alert("不能为空");
		return false;
	}
	return true;
}
function del(id){
	if(id!=0){
		location='<c:url value="/admin/clazz/fileType.do?m=del&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("menuId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/clazz/fileType.do?m=del&ids="/>'+idstr;
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
<jsp:include page="pub/left_clazz.jsp"></jsp:include>
</td>
<td valign="top">
	<div class="middle">
		<form action="<c:url value="/admin/clazz/fileType.do?m=add"/>" method="post" style="margin:0px;" onsubmit="return check(this)">
		<table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
			<tr>
				<td width="20%" height="30" bgcolor="#ffffff" align="center">目录名称:</td>
			    <td width="60%" height="30" bgcolor="#ffffff" align="center">
					<input type="text" name="name"/>	    	
			    </td>
			    <td width="20%" height="30" bgcolor="#ffffff" align="center">
					<input type="submit" value="添加"/>			    
			    </td>
			</tr>
		</table>
		</form>
	</div>
	<div id="c01">
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">ID</div></td>
	      <td width="60%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">名称</div></td>
	      <td width="20%" height="30" bgcolor="#e5edfa"><div align="center" class="STYLE3">操作选项 </div></td>
	    </tr>
	    <c:choose>
	    	<c:when test="${not empty fileTypeList}">
	    		<c:forEach items="${fileTypeList}" var="ft" varStatus="vs">
	    		<tr>
			      <td width="7%" height="30" bgcolor="#FFFFFF" align="center">
			      	<input type="checkbox" name="menuId" value="${ft.id}"/>&nbsp;<c:out value="${vs.index+1}" />
			       </td>
			      <td width="10%" height="30" bgcolor="#FFFFFF" align="center" id="menu${ft.id}">
			      	<c:out value="${ft.name}" />
			      </td>
			      <td width="10%" height="30" bgcolor="#FFFFFF" align="center">
			      	<a id="edit${ft.id}" href="javascript:edit(${ft.id},'${ft.name}')">修改</a> 
			      	<a id="edit_submit${ft.id}" href="javascript:edit_submit(${ft.id})" style="display: none">保存</a>
			      	<a href="javascript:del(${ft.id})">删除</a>
			     </td>
			    </tr>
	    		</c:forEach>
	    	</c:when>
	    	<c:otherwise>
	    		<tr>
			      <td colspan="3" align="center" height="30" bgcolor="#ffffff">
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
