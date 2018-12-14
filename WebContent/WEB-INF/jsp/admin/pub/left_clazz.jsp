<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<script type="text/javascript">
document.write("<ul>");
var url = document.URL;
if(url.indexOf("clazzMenu.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/clazz/clazzMenu.do?m=list"/>">课程目录管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/clazz/clazzMenu.do?m=list"/>">课程目录管理</a></li>');
}
if(url.indexOf("fileType.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/clazz/fileType.do?m=list"/>">文件类型</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/clazz/fileType.do?m=list"/>">文件类型</a></li>');
}

if(url.indexOf("add.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/clazz/add.do?m=to"/>">添加课程</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/clazz/add.do?m=to"/>">添加课程</a></li>');
}
if(url.indexOf("/news.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/clazz/clazz.do?m=list"/>">课程管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/clazz/clazz.do?m=list"/>">课程管理</a></li>');
}
document.write("</ul>");
</script>