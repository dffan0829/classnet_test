<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<script type="text/javascript">
document.write("<ul>");
var url = document.URL;
if(url.indexOf("menu.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/source/menu.do?m=list"/>">资源目录管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/source/menu.do?m=list"/>">资源目录管理</a></li>');
}
if(url.indexOf("/source.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/source/source.do?m=list"/>">资源管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/source/source.do?m=list"/>">资源管理</a></li>');
}
document.write("</ul>");
</script>