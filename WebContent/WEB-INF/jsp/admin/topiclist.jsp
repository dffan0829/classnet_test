<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--帖子管理</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function selectAll(obj){
	var array = document.getElementsByName("topicId");
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
		location='<c:url value="/admin/topic/topic.do?m=del&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("topicId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/topic/topic.do?m=del&ids="/>'+idstr;
		}
		else{
			alert("您还未选择");
		}
	}
}
function search(){
	var key = document.getElementById("key");
	var menuId = document.getElementById("menuId");
	var url = "<c:url value="/admin/topic/topic.do?m=list"/>";
	if(key.value!=""){
		url+="&key="+key.value;
	}
	if(menuId.value!=0){
		url+="&menuId="+menuId.value;
	}
	location=url;
}
function changeMenu(menuId){
	var url = '<c:url value="/admin/topic/topic.do?m=list"/>';
	if(menuId!=0){
		url+='&menuId='+menuId;
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
<jsp:include page="pub/left_topic.jsp"></jsp:include>
</td>
<td valign="top">
	<div id="c01">
	<dl>
	<dt style="padding-right:20px;">分类：
		<select id="menuId" name="menuId" style="width:100px;" onchange="changeMenu(this.value)">
		<option value="0">全部</option>
		<c:forEach items="${menuList}" var="menu">
			<option value="${menu.id}" ${menu.id==menuId?'selected=selected':''}><c:out value="${menu.name}"/></option>
		</c:forEach>
		</select>
	</dt>
	<dt>关键字:<input type="text" class="qidian" id="key" name="key" value="${key}"/> </dt>
	<dd><input type="button" value="查询" class="chaxun" onclick="search()"/></dd>
	</dl>
	  <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#dae2e5">
	    <tr>
	      <td width="8%" height="30" bgcolor="#e5edfa" align="center">ID</td>
	      <td height="30" bgcolor="#e5edfa" align="center">标题</td>
	      <td width="12%" height="30" bgcolor="#e5edfa" align="center">发布人</td>
	      <td width="15%" height="30" bgcolor="#e5edfa" align="center">发布时间</td>
	      <td width="8%" height="30" bgcolor="#e5edfa" align="center">回复数</td>
	      <td width="15%" height="30" bgcolor="#e5edfa" align="center">操作选项</td>
	    </tr>
		<c:choose>
		<c:when test="${not empty topicList}">
		<c:forEach items="${topicList}" var="topic" varStatus="vs">
		<tr>
	      <td height="30" bgcolor="#FFFFFF" align="center">
	        <input type="checkbox" name="topicId" value="${topic.id}"/> 
	        <c:out value="${vs.index+1}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="left" style="padding-left:10px;"><a href="<c:url value="/topic/topic.do?id=${topic.id}"/>" target="_blank"><c:out value="${topic.title}"/></a></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${topic.userEntity.username}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><f:formatDate value="${topic.pubtime}" pattern="yyyy-MM-dd HH:mm"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${topic.replyNum}" default="0"/></td>
	      <td width="10%" height="30" bgcolor="#FFFFFF" align="center">
	      	<a href="javascript:del(${topic.id})">删除</a>
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
