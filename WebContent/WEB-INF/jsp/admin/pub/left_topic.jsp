<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<script type="text/javascript">
document.write("<ul>");
var url = document.URL;
if(url.indexOf("menu.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/topic/topicMenu.do?m=list"/>">分类管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/topic/topicMenu.do?m=list"/>">分类管理</a></li>');
}
if(url.indexOf("topic.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/topic/topic.do?m=list"/>">帖子管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/topic/topic.do?m=list"/>">帖子管理</a></li>');
}
document.write("</ul>");
</script>