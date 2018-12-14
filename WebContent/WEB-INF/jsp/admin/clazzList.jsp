<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--课程列表</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function selectAll(obj){
	var array = document.getElementsByName("clazzId");
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
		location='<c:url value="/admin/clazz/clazz.do?m=del&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("clazzId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/clazz/clazz.do?m=del&ids="/>'+idstr;
		}
		else{
			alert("您还未选择");
		}
	}
}
function search(){
	var key = $("#key").val();
	var menuId = $("#menuId").val();
	var parentMenuId = $("#parentMenuId").val();
	var url = "<c:url value="/admin/clazz/clazz.do?m=list"/>";
	if(key!=""){
		url+="&key="+key;
	}
	if(parentMenuId!=0){
		url+="&pmId="+parentMenuId;
	}
	if(menuId&&menuId!=0){
		url+="&mId="+menuId;
	}
	if($("#fileTypeId").val()!=0){
		url+='&ftId='+$("#fileTypeId").val();
	}
	location=url;
}
function changestatus(id,status){
	$.ajax({
		url:"<c:url value="/admin/clazz/clazz.do"/>",
		type:"get",
		data:"m=changestatus&id="+id+"&status="+status,
		success:function(msg){
			if(msg=="1"){
				alert("操作成功");
			}
			else{
				alert("操作失败");
			}
		}
	});
}
function changeselect(){
	var url='<c:url value="/admin/clazz/clazz.do?m=list"/>';
	if($("#parentMenuId").val()!=0){
		url+='&pmId='+$("#parentMenuId").val();
	}
	var menuId=$("#menuId").val();
	if(menuId&&menuId!=0){
		url+='&mId='+menuId;
	}
	if($("#fileTypeId").val()!=0){
		url+='&ftId='+$("#fileTypeId").val();
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
<jsp:include page="pub/left_clazz.jsp"></jsp:include>
</td>
<td valign="top">
	<div id="c01">
	<dl>
	<dt style="padding-right:20px;">新闻栏目：
		<select id="parentMenuId" name="parentMenuId" style="width:100px;" onchange="changeselect()">
		<option value="0">全部</option>
		<c:forEach items="${menuList}" var="menu">
			<option value="${menu.id}" ${menu.id==param.pmId?'selected=selected':''}><c:out value="${menu.name}"/></option>
		</c:forEach>
		</select>
		<c:if test="${not empty childMenuList}">
		<select id="menuId" name="menuId" style="width:100px;" onchange="changeselect()">
			<option value="0">全部</option>
			<c:forEach items="${childMenuList}" var="cm">
				<option value="${cm.id}" ${cm.id==param.mId?'selected=selected':''}><c:out value="${cm.name}"/></option>
			</c:forEach>
		</select>
		</c:if>
		文件类型:
		<select id="fileTypeId" name="fileTypeId" style="width:100px;" onchange="changeselect()">
			<option value="0">全部</option>
			<c:forEach items="${fileTypeList}" var="ft">
				<option value="${ft.id}" ${ft.id==param.ftId?'selected=selected':''}><c:out value="${ft.name}"/></option>
			</c:forEach>
		</select>
	</dt>
	<dt>关键字:<input type="text" class="qidian" id="key" name="key" value="${key}"/> </dt>
	<dd><input type="button" value="查询" class="chaxun" onclick="search()"/></dd>
	</dl>
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">ID</td>
	      <td width="30%" height="30" bgcolor="#e5edfa" align="center">标题</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">发布时间</td>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">点击量</td>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center"></td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">操作选项</td>
	    </tr>
		<c:choose>
		<c:when test="${not empty clazzList}">
		<c:forEach items="${clazzList}" var="clazz" varStatus="vs">
		<tr>
	      <td height="30" bgcolor="#FFFFFF" align="center">
	        <input type="checkbox" name="clazzId" value="${clazz.id}"/> 
	        <c:out value="${vs.index+1}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="left" style="padding-left:10px;"><a href="<c:url value="/clazz/view.do?id=${clazz.id}"/>" target="_blank"><c:out value="${clazz.name}"/></a></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><f:formatDate value="${clazz.pubtime}" pattern="yyyy-MM-dd HH:mm"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${clazz.viewCount}"/></td>
	       <td height="30" bgcolor="#FFFFFF" align="center">
	       	<select onchange="changestatus(${clazz.id},this.value)">
	       		<option value="1" ${clazz.status==1?'selected=selected':''}>正常</option>
	       		<option value="2" ${clazz.status==2?'selected=selected':''}>推荐</option>
	       	</select>
	       </td>
	      <td width="10%" height="30" bgcolor="#FFFFFF" align="center">
	      	<a href="<c:url value="/admin/clazz/clazz.do?m=edit&id=${clazz.id}"/>">修改</a> 
	      	<a href="javascript:del(${clazz.id})">删除</a>
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
	  <p><input type="checkbox" onclick="selectAll(this)"/> 全选 
	  <input type="button" value="删除" class="shanchu" onclick="del(0)"/></p>
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
