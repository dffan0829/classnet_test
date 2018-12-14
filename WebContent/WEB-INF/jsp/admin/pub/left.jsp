<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<script type="text/javascript">
document.write('<ul>');
var url = document.URL;
if(url.indexOf("user.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/user.do?m=list"/>">用户管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/user.do?m=list"/>">用户管理</a></li>');
}
if(url.indexOf("homework.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/homework.do?m=titleList"/>">作业管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/homework.do?m=titleList"/>">作业管理</a></li>');
}
document.write('</ul>');
</script>