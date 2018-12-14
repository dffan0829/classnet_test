<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理--新闻</title>
<link href="<%=request.getContextPath() %>/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function selectAll(obj){
	var array = document.getElementsByName("newsId");
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
		location='<c:url value="/admin/news/news.do?m=del&id="/>'+id;
	}
	else{
		var array = document.getElementsByName("newsId");
		var idstr="";
		for(var i=0;i<array.length;i++){
			if(array[i].checked){
				idstr+=array[i].value+",";
			}
		}
		if(idstr){
			location='<c:url value="/admin/news/news.do?m=del&ids="/>'+idstr;
		}
		else{
			alert("您还为选择");
		}
	}
}
function search(){
	var key = document.getElementById("key");
	var menuId = document.getElementById("menuId");
	var url = "<c:url value="/admin/news/news.do?m=list"/>";
	if(key.value!=""){
		url+="&key="+key.value;
	}
	if(menuId.value!=0){
		url+="&menuId="+menuId.value;
	}
	location=url;
}
function changestatus(id,status){
	$.ajax({
		url:"<c:url value="/admin/news/news.do"/>",
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
function changeMenu(menuId){
	var url = '<c:url value="/admin/news/news.do?m=list"/>';
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
<jsp:include page="pub/left_news.jsp"></jsp:include>
</td>
<td valign="top">
	<div id="c01">
	<dl>
	<dt style="padding-right:20px;">新闻栏目：
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
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">ID</td>
	      <td width="30%" height="30" bgcolor="#e5edfa" align="center">标题</td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">发布时间</td>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center">点击量</td>
	      <td width="10%" height="30" bgcolor="#e5edfa" align="center"></td>
	      <td width="20%" height="30" bgcolor="#e5edfa" align="center">操作选项</td>
	    </tr>
		<c:choose>
		<c:when test="${not empty newsList}">
		<c:forEach items="${newsList}" var="news" varStatus="vs">
		<tr>
	      <td height="30" bgcolor="#FFFFFF" align="center">
	        <input type="checkbox" name="newsId" value="${news.id}"/> 
	        <c:out value="${vs.index+1}"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="left" style="padding-left:10px;"><a href="<c:url value="/news/see.do?id=${news.id}"/>" target="_blank"><c:out value="${news.title}"/></a></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><f:formatDate value="${news.pubtime}" pattern="yyyy-MM-dd HH:mm"/></td>
	      <td height="30" bgcolor="#FFFFFF" align="center"><c:out value="${news.viewNum}"/></td>
	       <td height="30" bgcolor="#FFFFFF" align="center">
	       	<select onchange="changestatus(${news.id},this.value)">
	       		<option value="1" ${news.status==1?'selected=selected':''}>普通</option>
	       		<option value="2" ${news.status==2?'selected=selected':''}>推荐</option>
	       		<option value="3" ${news.status==3?'selected=selected':''}>栏目头条</option>
	       		<option value="4" ${news.status==4?'selected=selected':''}>首页大图新闻</option>
	       	</select>
	       </td>
	      <td width="10%" height="30" bgcolor="#FFFFFF" align="center">
	      	<a href="<c:url value="/admin/news/news.do?m=edit&id=${news.id}"/>">修改</a> 
	      	<a href="javascript:del(${news.id})">删除</a>
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
