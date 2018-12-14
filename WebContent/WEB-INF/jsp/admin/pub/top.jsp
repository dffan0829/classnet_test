<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<div class="header">
<h1 class="logo">后台管理</h1>
<div class="nav">
<ul>
<c:choose>
<c:when test="${not empty m}">
	<c:choose>
		<c:when test="${m=='index'}"><li class="bj">首页</li></c:when>
		<c:otherwise><li><a href="<c:url value="/admin/index.do"/>">首页</a></li></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${m=='news'}"><li class="bj">新闻</li></c:when>
		<c:otherwise><li><a href="<c:url value="/admin/index.do?m=news"/>">新闻</a></li></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${m=='clazz'}"><li class="bj">课程中心</li></c:when>
		<c:otherwise><li><a href="<c:url value="/admin/index.do?m=clazz"/>">课程中心</a></li></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${m=='source'}"><li class="bj">资源下载</li></c:when>
		<c:otherwise><li><a href="<c:url value="/admin/index.do?m=source"/>">资源下载</a></li></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${m=='topic'}"><li class="bj">互动交流</li></c:when>
		<c:otherwise><li><a href="<c:url value="/admin/index.do?m=topic"/>">互动交流</a></li></c:otherwise>
	</c:choose>
</c:when>
<c:otherwise>
<script type="text/javascript">
var url = document.URL;
document.write('<li><a href="<c:url value="/admin/index.do"/>">首页</a></li>');
if(url.indexOf("/news/")!=-1){
	document.write('<li class="bj">新闻</li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/index.do?m=news"/>">新闻</a></li>');
}
if(url.indexOf("/clazz")!=-1){
	document.write('<li class="bj">课程中心</li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/index.do?m=clazz"/>">课程中心</a></li>');
}
if(url.indexOf("/source")!=-1){
	document.write('<li class="bj">资源下载</li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/index.do?m=source"/>">资源下载</a></li>');
}
if(url.indexOf("/topic/")!=-1){
	document.write('<li class="bj">互动交流</li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/index.do?m=topic"/>">互动交流</a></li>');
}
</script>
</c:otherwise>
</c:choose>
</ul>
</div>
<div class="clear"></div>
<div class="king">
<p>我的信息：管理供用信息</p>
</div>
</div>